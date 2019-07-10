package sustain.entity;

import com.zjydt.sustain.common.entity.ExchangeQueue;
import lombok.Data;

import java.io.Serializable;

@Data
public class MqData implements Serializable {
    ExchangeQueue exchangeQueue;
    com.zjydt.sustain.common.entity.Message message;
}
