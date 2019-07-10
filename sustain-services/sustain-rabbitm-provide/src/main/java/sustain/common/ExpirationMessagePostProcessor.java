package sustain.common;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 消息过期
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {
    private final Long ttl;

    public ExpirationMessagePostProcessor(Long ttl) {
        if(ttl==null){
            this.ttl = 0L;
        }else{
            this.ttl = ttl*1000;
        }

    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setExpiration(ttl.toString());

        return message;
    }
}
