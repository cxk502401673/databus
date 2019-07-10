package sustain.controller;

import com.zjydt.sustain.common.dto.PerceptInfoDto;
import com.zjydt.sustain.common.entity.Message;
import com.zjydt.sustain.common.entity.Person;
import com.zjydt.sustain.common.entity.PushData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sustain.common.DetailRes;
import sustain.common.ExpirationMessagePostProcessor;
import sustain.entity.MqData;
import sustain.service.RabbitmqService;
import sustain.service.Sender;

import javax.annotation.Resource;

/**
 * 消息接口.
 *
 * @author dax.
 * @version v1.0
 * @since 2018 /2/23 17:27
 */
@Slf4j
@RestController
public class SendController {
    @Resource
    private Sender sender;
    @Autowired
    private RabbitmqService rabbitmqService;

//    @RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json")
//    public DetailRes send(@RequestBody Message message) {
//        Long overTime=message.getOverTime();
//        if (overTime == null || overTime <= 0) {
//            return sender.send( message.getExchange(), message.getKey(),
//                    message.getMsgs(),null ,new CorrelationData(String.valueOf(message.getId())));
//        }else{
//            return sender.send(
//                    message.getExchange(),
//                    message.getKey(),
//                    message.getMsgs(),
//                    new ExpirationMessagePostProcessor(overTime)
//                    ,new CorrelationData(String.valueOf(message.getId())));
//        }
//
//    }
    @RequestMapping(value = "/mqData", method = RequestMethod.POST, consumes = "application/json")
    public DetailRes send(@RequestBody MqData mqData) {
        rabbitmqService.createAndBindQueueExchange(mqData.getExchangeQueue());
        return sender.sendMessage(mqData.getMessage());
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json")
    public DetailRes send(@RequestBody Message message) {

        return sender.sendMessage(message);
    }


    /**
     * 发送 匹配结果
     * @param pushData
     * @return
     */
    @RequestMapping(value = "/pushData", method = RequestMethod.POST, consumes = "application/json")
    public DetailRes send(@RequestBody PushData pushData) {
        log.info("get in sendPushData success !");
        return sender.sendMessage(pushData);
    }

    /**
     * 发送到 失败队列
     * @param perceptInfoDto
     * @return
     */
    @RequestMapping(value = "/sendToFailQueue", method = RequestMethod.POST, consumes = "application/json")
    public DetailRes sendToFailQueue(@RequestBody PerceptInfoDto perceptInfoDto) {

        return sender.sendToFailQueue(perceptInfoDto);
    }

}
