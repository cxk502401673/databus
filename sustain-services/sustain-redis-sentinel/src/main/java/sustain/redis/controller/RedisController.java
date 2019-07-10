package sustain.redis.controller;

import com.zjydt.sustain.common.entity.DetailRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustain.redis.common.RedisUtil;
@RestController
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/hset")
    public DetailRes hset(@RequestParam("key") String key, @RequestParam("field") String field, @RequestParam("value") String value) {
        if (redisUtil.hset(key, field, value)) {
            return new DetailRes(true, "操作成功 ");
        } else {
            return new DetailRes(false, "redis hset 出错");
        }
    }

    @RequestMapping(value = "/set")
    public DetailRes set(@RequestParam("key") String key,   @RequestParam("value") String value) {
        if (redisUtil.set(key, value)) {
            return new DetailRes(true, "操作成功 ");
        } else {
            return new DetailRes(false, "redis set 出错");
        }
    }
}
