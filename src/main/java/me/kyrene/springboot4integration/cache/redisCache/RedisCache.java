package me.kyrene.springboot4integration.cache.redisCache;

import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.utils.redis.JedisUtil;
import me.kyrene.springboot4integration.utils.serialize.SerializeUtil;
import org.junit.Test;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by wanglin on 2018/1/11.
 */
@Component("redisCache")
public class RedisCache {

    /**
     * 从Redis缓存获取数据
     * @param redisKey
     * @return
     */
    public Object getDataFromRedis(String redisKey){
        JedisUtil jedis = JedisUtil.getInstance();
        byte[] byteArray = jedis.getDataFromRedis(redisKey.getBytes());

        if(byteArray != null){
            return SerializeUtil.unSerialize(byteArray);
        }
        return null;
    }
    /**
     * 保存数据到Redis
     * @param redisKey
     */
    public String saveDataToRedis(String redisKey,Object obj){

        byte[] bytes = SerializeUtil.serialize(obj);
        JedisUtil jedis = JedisUtil.getInstance();
        String code = jedis.setDataToRedis(redisKey.getBytes(), bytes);

        return code;
    }

//    @Test
//    public void test01(){
//        User user = new User();
//        user.setAge(33);
//        user.setName("wang");
//        user.setId(5L);
//        String wanglin = saveDataToRedis("wanglin", user);
//        User wanglin1 = (User) getDataFromRedis("wanglin");
//        System.out.println("ooo");
//
//    }
}
