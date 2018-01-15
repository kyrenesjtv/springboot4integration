package me.kyrene.springboot4integration.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.Properties;

/**
 * Created by wanglin on 2018/1/2.
 */
public class JedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisUtil.class);

    private static JedisPool jedisPool = null;

    private static final JedisUtil jedisUtil = new JedisUtil();

    private JedisUtil() {

    }

    static {
        Properties properties = PropertyUtil.loadProperties("application.properties");
        String host = properties.getProperty("spring.redis.host");
        String port = properties.getProperty("spring.redis.port");
        String pass = properties.getProperty("spring.redis.password");
        String timeout = properties.getProperty("spring.redis.timeout");
        String maxIdle = properties.getProperty("spring.redis.pool.max-idle");
        String maxTotal = properties.getProperty("spring.redis.pool.max-total");
        String maxWaitMillis = properties.getProperty("spring.redis.pool.max-wait");
        String testOnBorrow = properties.getProperty("spring.redis.testOnBorrow");

        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(Integer.parseInt(maxTotal));
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(Integer.parseInt(maxIdle));
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));

        jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), pass);
    }

    /**
     * 获取jedis
     *
     * @return
     */
    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static JedisUtil getInstance() {
        return jedisUtil;
    }

    /**
     * 归还jedis
     *
     * @param jedis
     */
    private void returnResource(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
        }
    }

    /**
     * 关闭jedis
     *
     * @param jedis
     */
    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    /**
     * 设置String类型 键值
     *
     * @param key
     * @param value
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            close(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 返回 key 的值，如果 key 不存在时，返回 null。 如果 key 不是字符串类型，那么返回一个错误。
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            close(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 用于cache获取数据
     *
     * @param key
     * @return
     */
    public byte[] getDataFromRedis(byte[] key) {
        Jedis jedis = null;
        byte[] result = null;
        try {
            jedis = getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            close(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 用于cache设置数据
     *
     * @param key
     * @param value
     * @return
     */
    public String setDataToRedis(byte[] key, byte[] value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            close(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 从redis中删除数据
     * @param key
     * @return
     */
    public Long deleteDataFromRedis(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            close(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }
}
