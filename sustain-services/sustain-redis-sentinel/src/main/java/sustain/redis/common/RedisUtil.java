package sustain.redis.common;

import com.zjydt.sustain.common.util.SerializeUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Component
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 设置key
     *
     * @param key
     * @param value
     */
    public boolean set(final String key, final String value) {
//        redisTemplate.opsForValue().set(key, value);
        return (boolean) redisTemplate.execute(new RedisCallback() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.set(key.getBytes(), value.getBytes());
			}
		});
    }

    /**
     * set 对象
     * @param key
     * @param Obj
     * @return
     */

    public boolean set(  String key,   Object Obj) {
//        redisTemplate.opsForValue().set(key, value);
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.set(key.getBytes(), SerializeUtil.serialize(Obj));
            }
        });
    }

    /**
     * 设置对象(有过期时间）
     * @param key
     * @param Obj
     * @param second
     * @return
     */
    public boolean set(  String key, Object Obj,   int second) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setEx(key.getBytes(), second, SerializeUtil.serialize(Obj));
            }
        });
    }

    /**
     * 设置key(有过期时间）
     *
     * @param key
     * @param value
     */
    public boolean set(final String key, final String value, final int second) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setEx(key.getBytes(), second, value.getBytes());
            }
        });
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean hexists(final String key,final String field) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hExists(key.getBytes(),field.getBytes());
            }
        });
    }
    /**
     * 删除key
     *
     * @param key
     */
    public Boolean del(final String key) {
      return  redisTemplate.delete(key);


//        redisTemplate.execute(new RedisCallback() {
//            public Long doInRedis(RedisConnection connection) throws DataAccessException {
//                connection.del(key.getBytes());
//                return 1L;
//            }
//        });
    }
    /**
     * 获取缓存<br>
     * 注：该方法暂不支持Character数据类型
     * @param key   key
     * @param clazz 类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public   <T> T get(String key, Class<T> clazz) {
        return (T)redisTemplate.boundValueOps(key).get();
    }
    /**
     * 通过key获取value
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return (String)redisTemplate.opsForValue().get(key);
		/*return (String) redisTemplate.execute(new RedisCallback() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] values = connection.get(key.getBytes());
				if (null != values) {
			    	return new String(values);
				}
				return "";
			}
		});*/
    }

    /**
     * 通过key + field获取value
     *
     * @param key,field
     * @return
     */
    public String hget(final String key, final String field) {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] values = connection.hGet(key.getBytes(), field.getBytes());
                if (null != values) {
                    return new String(values);
                }
                return "";
            }
        });
    }

    /**
     * 通过key + field设置value
     *
     * @param key,field
     * @return
     */
    public boolean hset(final String key, final String field, final String value) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hSet(key.getBytes(), field.getBytes(), value.getBytes());
            }
        });
    }

    /**
     * 模糊查找所有的key
     *
     * @param keyRegex
     */
    public Set<String> keys(final String keyRegex) {
        return redisTemplate.keys(keyRegex);
    }
    /**
     * 通过key 获取所有的field +value
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetall(final String key) {
        return (Map<String, String>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
                Map<byte[], byte[]> values = connection.hGetAll(key.getBytes());
                if (CollectionUtils.isEmpty(values)) {
                    return new HashMap<String, String>();
                } else {
                    // 将Map<byte[],byte[]>转换为Map<String,String>
                    Map<String, String> allValueMap = new HashMap<String, String>(values.size());
                    Iterator itor = values.keySet().iterator();
                    while (itor.hasNext()) {
                        byte[] mapKey = (byte[]) itor.next();
                        allValueMap.put(new String(mapKey), new String(values.get(mapKey)));
                    }
                    return allValueMap;
                }
            }
        });
    }

    /**
     * list push
     *
     * @param key
     * @param value
     */
    public void rpush(final String key, final String value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.rPush(key.getBytes(), value.getBytes());
                return 1L;
            }
        });
    }

    /**
     * list get range
     *
     * @param key
     * @param begin
     * @param end
     */
    public List<String> lrange(final String key, final long begin, final long end) {
        return (List<String>) redisTemplate.execute(new RedisCallback() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                List<byte[]> result = connection.lRange(key.getBytes(), begin, end);
                List<String> resultList = new ArrayList<>();
                for(byte[] r : result){//将字节数组转换为字符串数组
                    resultList.add(new String(r));
                }
                return resultList;
            }
        });
    }
}
