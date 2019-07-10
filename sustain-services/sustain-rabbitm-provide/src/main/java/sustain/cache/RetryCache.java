package sustain.cache;

import com.zjydt.sustain.common.entity.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import sustain.common.Constants;
import sustain.common.DetailRes;
import sustain.service.Sender;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 重新发送数据
 */
@Slf4j
@Data
public class RetryCache {
    private Sender sender;
    private boolean stop = false;
    public Map<String, Message> map = new ConcurrentSkipListMap<>();
    private AtomicLong id = new AtomicLong();
    private String aa;
    public void setSender(Sender sender) {
        this.sender = sender;
        startRetry();
    }

    public long generateId() {
        return id.incrementAndGet();
    }

    public void add(Message message) {
        map.putIfAbsent(message.getId(), message);
    }

    public void del(String id) {
        map.remove(id);
    }

    private void startRetry() {
        new Thread(() ->{
            while (!stop) {
                try {
                    Thread.sleep(Constants.RETRY_TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long now = System.currentTimeMillis();

                for (Map.Entry<String, Message> entry : map.entrySet()) {
                    Message messageWithTime = entry.getValue();

                    if (null != messageWithTime) {
                        if (messageWithTime.getTimestamp() + 3 * Constants.VALID_TIME < now) {
                            log.info("send message {} failed after 3 min ", messageWithTime);
                            del(entry.getKey());
                        } else if (messageWithTime.getTimestamp() + Constants.VALID_TIME < now) {
                           // DetailRes res = null;
                            try {
                                 sender.sendMessage(messageWithTime);
                                //res = sender.sendMessage(messageWithTime);
                            } catch (Exception e) {
                                log.error("重试发送失败",e);
                            }

//                            if (!res.success()) {
//                                log.info("retry send message failed {} errMsg {}", messageWithTime, res.getErrMsg());
//                            }
                        }
                    }
                }
            }
        }).start();
    }
}
