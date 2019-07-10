package sustain.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import sustain.cache.RetryCache;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class AmqpConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;

//
//    @Value("${spring.rabbitmq.port}")
//    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;
    @Autowired
    RetryCache retryCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(addresses);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setPublisherConfirms(publisherConfirms);
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        cachingConnectionFactory.setChannelCacheSize(10);
        return cachingConnectionFactory;
    }
    @Bean
    public RetryCache retryCache() {
        return new RetryCache();
    }
    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack() {
        return new MsgSendConfirmCallBack();
    }

    @Bean
    public MsgSendReturnCallback msgSendReturnCallback() {
        return new MsgSendReturnCallback();
    }

    @Bean(name = "retryTemplate")
    public RetryTemplate attemptsRetry() {
        RetryTemplate retryTemplate = new RetryTemplate();
        //retryTemplate.setListeners(RetryConfig.getRetryListener());
        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        //设置重试异常和是否重试
        retryableExceptions.put(AmqpException.class, true);
        //设置重试次数和要重试的异常
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(5, retryableExceptions);
        retryTemplate.setRetryPolicy(retryPolicy);
        FixedBackOffPolicy backoffPolicy = new FixedBackOffPolicy();
        //设置重试间隔 mills
        backoffPolicy.setBackOffPeriod(5000);
        retryTemplate.setBackOffPolicy(backoffPolicy);
        return retryTemplate;
    }

    @Bean
    public RabbitTemplate reTryrabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange("fail_message_exchange");
        // template.setQueue("");
        template.setRoutingKey("fail_message_exchange");
        template.setRetryTemplate(attemptsRetry());
        return template;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        // template.setMessageConverter(); 可以自定义消息转换器  默认使用的JDK的，所以消息对象需要实现Serializable
        template.setMessageConverter(new Jackson2JsonMessageConverter());

        /**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        template.setConfirmCallback(msgSendConfirmCallBack());

        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        template.setReturnCallback(msgSendReturnCallback());
        template.setMandatory(true);
        return template;
    }

    public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

        /**
         * 当消息发送到交换机（exchange）时，该方法被调用.
         * 1.如果消息没有到exchange,则 ack=false
         * 2.如果消息到达exchange,则 ack=true
         *
         * @param correlationData
         * @param ack
         * @param cause
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
           // System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData);
            if (ack) {
                //当消息发送成功后，删除缓存中需要重发的消息
                //System.err.println("消息发送到 exchange 成功");
            } else {
               // System.err.println("消息发送到 exchange 失败");
                log.error("消息发送到 exchange 失败",correlationData.getId());
            }
        }
    }

    public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

        /**
         * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
         * 需要注意的是：该方法调用后，MsgSendConfirmCallBack中的confirm方法也会被调用，且ack = true
         *
         * @param message
         * @param replyCode
         * @param replyText
         * @param exchange
         * @param routingKey
         */


        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message,
                                    int replyCode, String replyText, String exchange, String routingKey) {
           log.error("MsgSendReturnCallback [消息从交换机到队列失败]  message：" + message);
            RepublishMessageRecoverer recoverer = new RepublishMessageRecoverer(reTryrabbitTemplate());
            Throwable cause = new Exception(new Exception("route_fail_and_republish"));
            recoverer.recover(message,cause);
            log.error("Returned Message："+replyText);


//            log.error(MessageFormat.format("消息发送ReturnCallback:{0},{1},{2},{3},{4},{5}",
//                    message, replyCode, replyText, exchange, routingKey));
//            rabbitTemplate.send("fail_message_exchange","fail_message_exchange",message);
        }
    }

    //  a  创建 fail_message 队列，存放发送失败的消息
    @Bean(name = "fail_message") //
    public Queue queueMessage() {
        return new Queue("fail_message");  //
    }

    //  b. 创建直连交换机 fail_message_exchange ，不需要routingkey
    @Bean(name="fanoutExchange")
    public FanoutExchange exchange() {
        return new FanoutExchange("fail_message_exchange");
    }

    /*
      c. 根据绑定规则将队列绑定到相应的交换机上（bindingKey）--Exchange Queues

     */
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("fail_message") Queue queueMessage,
                                          @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessage).to(fanoutExchange);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //  a  创建 v2 队列，存放发送失败的消息
    @Bean(name = "v2") //
    public Queue v2() {
        return new Queue("v2");  //
    }

    //  b. 创建直连交换机 v2 ，不需要routingkey
    @Bean("v2Exchange")
    public TopicExchange v2Exchange() {
        return new TopicExchange("v2");
    }

    @Bean
    public Binding bindingv2(@Qualifier("v2") Queue queueMessage,
                             @Qualifier("v2Exchange") TopicExchange v2Exchange) {
        return BindingBuilder.bind(queueMessage).to(v2Exchange).with("#");
    }





//    //  a  创建queue
//    @Bean(name = "message") //  指定该参数名是message  见下面Qualifier（"message"）
//    public Queue queueMessage(){
//        return new Queue("topic.message");  //  topic.message  是rounting-key,匹配规则
//    }
//
//    @Bean(name = "notes")
//    public Queue queueMessages(){
//        return new Queue("topic.notes");
//    }
//
//    @Bean(name = "all")
//    public Queue queueallMessages(){
//        return new Queue("topic.all");
//    }
//    //  b. 创建交换机TopicExchange
//    @Bean
//    public TopicExchange exchange(){
//        return new TopicExchange(exchange);
//    }
//
//    /*
//      c. 根据绑定规则将队列绑定到相应的交换机上（bindingKey）--Exchange Queues
//
//     */
//    @Bean
//    public Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage,TopicExchange exchange){
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//    }
//
//    @Bean
//    public Binding bindingNotesExchangeMessage(@Qualifier("notes") Queue queueMessage,TopicExchange exchange){
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.notes");
//    }
//    @Bean
//    /**
//     * 绑定， 匹配 runtingkey 为 topic.#的所有消息 发送到all队列 中
//     *
//     */
//    public  Binding bindingExchangeMessages(@Qualifier("all")Queue queueMessages, TopicExchange exchange){
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
