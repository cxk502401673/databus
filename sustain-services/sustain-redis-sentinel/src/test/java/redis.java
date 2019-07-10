import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sustain.redis.SpringBootRedisClusterApplication;
import sustain.redis.common.RedisUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRedisClusterApplication.class)
@WebAppConfiguration
public class redis {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void redisStringUtils() {
        if(redisUtil.hexists("duowei","330721199010141411")){
            System.out.println("duowei 存在key 330721199010141411");
        }else{
            System.out.println("duowei 不存在key 330721199010141411");
        }

        if(redisUtil.hexists("taofan","330721199010141411")){
            System.out.println("taofan 存在key 330721199010141411");
        }else{
            System.out.println("taofan 不存在key 330721199010141411");
        }


        redisUtil.hset("duowei","330721199010141411","{\"name\":\"cxk1\"}");
        redisUtil.hset("duowei","330721199710141411","{\"name\":\"cxk2\"}");
        redisUtil.hset("taofan","330721199010141412","{\"name\":\"ccc1\"}");
        redisUtil.hset("taofan","330721196010141411","{\"name\":\"ccc2\"}");
        redisUtil.hset("taofan","330721189010141413","{\"name\":\"ccc3\"}");
       // redisUtil.set("xxx","xxx",60);
        System.out.println("--"+redisUtil.hget("duowei","330721199010141411"));
        System.out.println("--"+redisUtil.hget("duowei","330721199010141412"));

        System.out.println("--"+redisUtil.hget("taofan","330721199010141412"));
        System.out.println("--"+redisUtil.hget("taofan","330721199010141411"));
    }

}
