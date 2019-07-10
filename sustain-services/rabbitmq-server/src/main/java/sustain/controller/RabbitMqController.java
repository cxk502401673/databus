package sustain.controller;

import com.rabbitmq.client.AMQP;
import com.zjydt.sustain.common.entity.Exchange;
import com.zjydt.sustain.common.entity.ExchangeQueue;
import com.zjydt.sustain.common.entity.Queue;
import com.zjydt.sustain.common.enums.ResponseCodeEnum;
import com.zjydt.sustain.common.util.ApiResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sustain.util.RabbitmqUtil;

import javax.annotation.Resource;
import java.io.IOException;
@Slf4j
@RestController
public class RabbitMqController {
    @Resource
    RabbitmqUtil rabbitmqUtil;



    @RequestMapping(value = "/createAndBindQueueExchange", method = RequestMethod.POST, consumes = "application/json")
    public ApiResult<String> createAndBindQueueExchange(@RequestBody ExchangeQueue exchangeQueue ) {
        //创建队列
        try {
            Queue q=new Queue();
            q.setOverTime(exchangeQueue.getOverTime());
            q.setQueueName(exchangeQueue.getQueue());
            q.setArgs(exchangeQueue.getArgs());
            rabbitmqUtil.createQueue(q);
        } catch (Exception e) {
            log.error("创建队列出错",e);
            return new ApiResult<>(exchangeQueue.getQueue(), ResponseCodeEnum.FAIL);
        }
        //创建交换器
        try {
            if(StringUtils.isNotBlank(exchangeQueue.getKey())){
                rabbitmqUtil.createExchange(exchangeQueue.getExchange(),
                        exchangeQueue.getType(),exchangeQueue.getDurable(),exchangeQueue.getArgs());
            }else{
                rabbitmqUtil.createFanoutExchange(exchangeQueue.getExchange());
            }

        } catch (Exception e) {
            log.error("创建交换器 出错",e);
            return new ApiResult<>(exchangeQueue.getExchange(), ResponseCodeEnum.FAIL);
        }

        try {

            if(StringUtils.isNotBlank(exchangeQueue.getKey())){
                rabbitmqUtil.bindExchange_Queue(exchangeQueue.getQueue(),exchangeQueue.getExchange(),exchangeQueue.getKey());
            }else{
                rabbitmqUtil.bindExchange_Queue(exchangeQueue.getQueue(),exchangeQueue.getExchange());
            }


        } catch (Exception e) {
            log.error("绑定交换器 和 队列 出错",e);
            return new ApiResult<>("绑定交换器 和 队列",ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>(exchangeQueue.getExchange()+","+exchangeQueue.getQueue(), ResponseCodeEnum.SUCCESS);
    }


    /**
     * 创建队列
     * @param queue
     * @throws IOException
     */
    @RequestMapping(value = "/createQueue", method = RequestMethod.POST, consumes = "application/json")
    public ApiResult<Queue> createQueue(@RequestBody Queue queue)  {

        try {
            rabbitmqUtil.createQueue(queue);
        } catch (Exception e) {
            log.error("创建队列出错",e);
            return new ApiResult<>(queue, ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>(queue, ResponseCodeEnum.SUCCESS);
    }

    /**
     * 删除队列
     * @param queue
     * @throws IOException
     */
    @RequestMapping(value = "/delQueue", method = RequestMethod.POST, consumes = "application/json")
    public ApiResult<String> delQueue(@RequestBody String queue) {
        try {
            rabbitmqUtil.delQueue(queue);
        } catch (Exception e) {
            log.error("删除队列 出错",e);
            return new ApiResult<>(queue, ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>(queue, ResponseCodeEnum.SUCCESS);

    }

    /**
     * 创建交换器
     * @param exchange
     * @throws IOException
     */
    @ApiOperation(value = "创建交换器", notes = "创建交换器", httpMethod = "POST")
    @ApiImplicitParam(name = "exchange", value = "交换器", required = true, dataType = "Exchange")
    @RequestMapping(value = "/createExchange", method = RequestMethod.POST, consumes = "application/json")
    public ApiResult<Exchange> createExchange(@RequestBody Exchange exchange ){
        try {
            AMQP.Exchange.DeclareOk exchange1 = rabbitmqUtil.createExchange(exchange.getExchange(),
                    exchange.getType(),
                    exchange.getDurable(), exchange.getArgs());
            System.out.println(exchange1);
        } catch (Exception e) {
            log.error("创建交换器 出错",e);
            return new ApiResult<>(exchange, ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>(exchange, ResponseCodeEnum.SUCCESS);

    }

    /**
     * 删除交换器
     * @param exchange
     * @throws IOException
     */
    @RequestMapping(value = "/delExchange", method = RequestMethod.POST, consumes = "application/json")
    public ApiResult<Exchange> delExchange(@RequestBody Exchange exchange )  {
        try {
            AMQP.Exchange.DeleteOk a= rabbitmqUtil.delExchange(exchange.getExchange(),exchange.getDel());
            System.out.println(a);
        } catch (Exception e) {
            log.error("删除交换器 出错",e);
            return new ApiResult<>(exchange, ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>(exchange, ResponseCodeEnum.SUCCESS);

    }


    /**
     * 绑定交换器 和 队列

     * @throws IOException
     */
    @RequestMapping(value = "/bindExchangeQueue", method = RequestMethod.GET)
    public ApiResult<String> bindExchangeQueue(String queueName,String exchangeName,String routingKey)  {
        try {
            rabbitmqUtil.bindExchange_Queue(queueName,exchangeName,routingKey);
        } catch (Exception e) {
            log.error("绑定交换器 和 队列 出错",e);
            return new ApiResult<>("绑定交换器 和 队列",ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>("绑定交换器 和 队列",ResponseCodeEnum.SUCCESS);

    }
    /**
     * 取消绑定交换器 和 队列

     * @throws IOException
     */
    @RequestMapping(value = "/unbindExchangeQueue", method = RequestMethod.GET)
    public ApiResult<String> unbindExchangeQueue(String queueName,String exchangeName,String routingKey)  {
        try {
            rabbitmqUtil.queueUnbind(queueName,exchangeName,routingKey);
        } catch (Exception e) {
            log.error("取消绑定交换器 和 队列 出错",e);
            return new ApiResult<>("取消绑定交换器 和 队列 出错", ResponseCodeEnum.FAIL);
        }
        return new ApiResult<>("取消绑定交换器 和 队列 成功", ResponseCodeEnum.SUCCESS);

    }
}
