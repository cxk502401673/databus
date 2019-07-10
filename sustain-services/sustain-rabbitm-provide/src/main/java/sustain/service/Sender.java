package sustain.service;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.zjydt.sustain.common.dto.PerceptInfoDto;
import com.zjydt.sustain.common.entity.Message;
import com.zjydt.sustain.common.entity.PushData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sustain.cache.RetryCache;
import sustain.common.DetailRes;
import sustain.common.ExpirationMessagePostProcessor;

@Slf4j
@Service
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    RetryCache retryCache;

    @Value("${rabbitmq.exchange:#{null}}")
    private String exchange;

    public <T> DetailRes send(String ex, String routkey, T data, ExpirationMessagePostProcessor time,
                              CorrelationData correlationData) {
        if (data == null)
            return new DetailRes(false, "data is  null !!!", "");
        String objectJson = "";
        // 基本类型 直接处理
        if (data.getClass().isPrimitive()) {
            objectJson = data.toString();
            send(exchange, routkey, objectJson, time, correlationData);
        } else {
            try {
                objectJson = JSONObject.toJSONString(data);
            } catch (Exception e) {
                log.error("data  转换错误", e);
                return new DetailRes(false, "data 转换错误 !!!", "");
            }
            send(exchange, routkey, objectJson, time, correlationData);
        }
        return new DetailRes(true, "", objectJson);

    }

    public void send(String ex, String routkey, String jsonObj, ExpirationMessagePostProcessor time,
                     CorrelationData correlationData) {
        if(StringUtils.isBlank(ex)){
            ex=exchange;
        }
        try {
            if (time == null) {
                this.rabbitTemplate.convertAndSend(ex, routkey, jsonObj,
                        correlationData);
            } else {
                this.rabbitTemplate.convertAndSend(ex, routkey, jsonObj,
                        time,
                        correlationData);
            }
        } catch (Exception e) {
            log.error("====================send error !!================================", e);
        }

    }

    /**
     * 向   名称为 topicExchange 的交换机 发送满足routingkey规则为 "topic.messages " 的内容为 "msg 的消息
     */
    @SentinelResource("sendMessage")
    public DetailRes sendMessage(Message message) {
        String key = message.getKey();
        Long overTime = message.getOverTime();
       // log.debug("key:"+key);
      //  log.debug("message:"+JSONObject.toJSONString(message));
        try {
            String ex = message.getExchange();
          //  log.debug("ex:"+ex);
            if (StringUtils.isBlank(ex)) {
          //      log.debug("exchange:"+exchange);
                if (overTime == null || overTime <= 0) {
                    this.rabbitTemplate.convertAndSend(exchange, key, message.getMsgs(),
                            new CorrelationData(String.valueOf(message.getId())));


                } else {
                    this.rabbitTemplate.convertAndSend(exchange, key, message.getMsgs()
                            , new ExpirationMessagePostProcessor(overTime),
                            new CorrelationData(String.valueOf(message.getId())));
                }


            } else {
                if (overTime == null || overTime <= 0) {
                    this.rabbitTemplate.convertAndSend(ex, key, message.getMsgs(),
                            new CorrelationData(String.valueOf(message.getId())));
                } else {
                    this.rabbitTemplate.convertAndSend(ex, key, message.getMsgs(),
                            new ExpirationMessagePostProcessor(overTime),
                            new CorrelationData(String.valueOf(message.getId())));
                }

            }

        } catch (Exception e) {
            log.error("buildTopicMessageSender 出错", e);
            return new DetailRes(false, "sendMessage 出错");
        }
        if (message.getReturnMsg()) {
            return new DetailRes(true, "", message);
        } else {
            return new DetailRes(true, "", message.getId());
        }


    }

    @SentinelResource("sendPushData")
    public DetailRes sendMessage(PushData pushData) {

        Long overTime = pushData.getOverTime();
        try {
            if (overTime == null || overTime <= 0) {
                this.rabbitTemplate.convertAndSend(pushData.getExchangeName(), "", pushData,
                        new CorrelationData(String.valueOf(pushData.getId())));
            } else {
                this.rabbitTemplate.convertAndSend(pushData.getExchangeName(), "", pushData,
                        new ExpirationMessagePostProcessor(overTime),
                        new CorrelationData(String.valueOf(pushData.getId())));
            }


        } catch (Exception e) {
            log.error("send PushData  error !" + JSONObject.toJSON(pushData).toString(), e);
            return new DetailRes(false, "send PushData  error");
        }
        log.info("sendPushData success !");
        return new DetailRes(true, "", JSONObject.toJSON(pushData));

    }

    @SentinelResource("sendToFailQueue")
    public DetailRes sendToFailQueue(PerceptInfoDto perceptInfoDto) {


        try {

            this.rabbitTemplate.convertAndSend("fail_message_exchange", "", perceptInfoDto
            );


        } catch (Exception e) {
            log.error("send PushData  error !" + JSONObject.toJSON(perceptInfoDto).toString(), e);
            return new DetailRes(false, "send PushData  error");
        }

        return new DetailRes(true, "", JSONObject.toJSON(perceptInfoDto));

    }

}
