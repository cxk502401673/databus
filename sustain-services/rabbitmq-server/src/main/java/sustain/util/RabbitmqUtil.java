package sustain.util;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.zjydt.sustain.common.entity.ExchangeQueue;
import com.zjydt.sustain.common.entity.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class RabbitmqUtil {
    @Resource
    private ConnectionFactory connectionFactory;


    /**
     * 创建队列
     *
     * @param queue
     * @throws IOException
     */
    public void createQueue(Queue queue) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Long overTime = queue.getOverTime();
        if(overTime!=null){
            if(overTime>0){
                map.put("x-message-ttl", queue.getOverTime() * 1000);
            }

        }
        Map<String,Object> args=queue.getArgs();
        if(args!=null){
            map.putAll(args);
        }
        log.info("创建队列 "+ JSONObject.toJSONString(queue));
        connectionFactory.createConnection().createChannel(false).queueDeclare(queue.getQueueName(),
                true, false, false, map);

    }

    /**
     * 删除队列
     *
     * @param queue
     * @throws IOException
     */
    public void delQueue(String queue) throws IOException {
        connectionFactory.createConnection().createChannel(false).queueDelete(queue);
    }

    /**
     * 创建交换器
     *
     * @param exchangeName
     * @param type
     * @param durable      持久
     * @return
     * @throws IOException
     */
    public   AMQP.Exchange.DeclareOk createExchange(String exchangeName, String type, Boolean durable, Map<String,Object> args) throws IOException {
      return  connectionFactory.createConnection().createChannel(false)
                .exchangeDeclare(exchangeName, type, durable,false,args);
    }

    /**
     * 创建 fanout 交换器（没有路由key）
     *
     * @param exchangeName
     * @return
     * @throws IOException
     */
    public FanoutExchange createFanoutExchange(String exchangeName) throws IOException {
        connectionFactory.createConnection().createChannel(false).exchangeDeclare(exchangeName, "fanout");
        return new FanoutExchange(exchangeName, true, false);
    }

    /**
     * 删除交换器
     *
     * @param exchangeName
     * @param del          如果为true，则表示仅在未使用时才删除该交换
     * @return
     * @throws IOException
     */
    public AMQP.Exchange.DeleteOk delExchange(String exchangeName, boolean del) throws IOException {

        return connectionFactory.createConnection().createChannel(false).exchangeDelete(exchangeName, del);
    }


    /**
     * 绑定交换机和队列
     *
     * @param QUEUE_NAME
     * @param EXCHANGE_NAME
     * @param ROUTING_KEY
     * @throws IOException
     */
    public void bindExchange_Queue(String QUEUE_NAME, String EXCHANGE_NAME, String ROUTING_KEY) throws IOException {
        // 建立一个绑定关系:
        connectionFactory.createConnection().createChannel(false).queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
    }

    /***
     * 绑定fanout 模式的交换器
     * @param QUEUE_NAME
     * @param EXCHANGE_NAME
     * @throws IOException
     */
    public void bindExchange_Queue(String QUEUE_NAME, String EXCHANGE_NAME) throws IOException {
        // 建立一个绑定关系:
        connectionFactory.createConnection().createChannel(false).queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
    }

    /**
     * 取消绑定交换机和队列
     *
     * @param QUEUE_NAME
     * @param EXCHANGE_NAME
     * @param ROUTING_KEY
     * @throws IOException
     */
    public void queueUnbind(String QUEUE_NAME, String EXCHANGE_NAME, String ROUTING_KEY) throws IOException {
        // 建立一个绑定关系:
        connectionFactory.createConnection().createChannel(false).queueUnbind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
    }
}
