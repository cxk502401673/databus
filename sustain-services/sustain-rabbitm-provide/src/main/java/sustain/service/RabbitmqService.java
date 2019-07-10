package sustain.service;


import com.zjydt.sustain.common.entity.ExchangeQueue;
import com.zjydt.sustain.common.util.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "rabbitmq-server")
public interface RabbitmqService {

    @RequestMapping(value = "/createAndBindQueueExchange")
    ApiResult<String> createAndBindQueueExchange(@RequestBody ExchangeQueue exchangeQueue);


}
