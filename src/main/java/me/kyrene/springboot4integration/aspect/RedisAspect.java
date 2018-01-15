package me.kyrene.springboot4integration.aspect;

import me.kyrene.springboot4integration.aspect.annotation.RedisCacheService;
import me.kyrene.springboot4integration.cache.redisCache.RedisCache;
import me.kyrene.springboot4integration.pojo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * aop 实现redis缓存处理
 * Created by wanglin on 2018/1/11.
 */
@Component
@Aspect
public class RedisAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAspect.class);

    @Autowired
    @Qualifier("redisCache")//如果RedisCache存在多个,就去找定义的redisCache
    private RedisCache redisCache;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut(value = "@annotation(me.kyrene.springboot4integration.aspect.annotation.RedisCacheService)")//定义的注解的类的全路径
    public void pointcutMethod() {

    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     *
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Method method = getMethod(joinPoint);
        //获取到注解的对象
        RedisCacheService cacheService = method.getAnnotation(RedisCacheService.class);
//        //判断是否使用缓存
        boolean use = cacheService.use();
        if (use) {
            switch (cacheService.cacheOperation()) {
                case GET:
                    result = executeDefault(cacheService, joinPoint, method);
                    break;
                case UPDATE:
                    result = executeUpdate(cacheService, joinPoint, method);
                    break;
                case DELETE:
                    result = executeDelete(cacheService, joinPoint, method);
                    break;
                case INSERT:
                    result = executeInsert(cacheService, joinPoint, method);
                    break;
                default:
                    result = joinPoint.proceed();
                    break;
            }
        } else {
            result = joinPoint.proceed();
        }
        return result;
    }

    /**
     * 插入
     *
     * @param cacheService
     * @param joinPoint
     * @param method
     * @return
     */
    private Object executeInsert(RedisCacheService cacheService, ProceedingJoinPoint joinPoint, Method method) {
        /**插入的时候有一个问题,ID如果是自增长的话，存入redis的时候是没有id的*/
        Object result = null;
        //user+session中的用户名(用户登录后将用户的信息保存在了session中)
        String redisKey = cacheService.group()[0] + ":zhangsan";
        User user = (User) joinPoint.getArgs()[0];//获取参数,如果是多个参数呢?
        try {
            result = joinPoint.proceed();//执行方法....这个就可以看成前置跟后置的分隔符
        } catch (Throwable e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        //后置：将数据库查到的数据保存到Redis
        String code = redisCache.saveDataToRedis(redisKey, user);
        if (code.equals("OK")) {
            LOGGER.info("**********数据成功插入到redis中!!!**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + result.toString());
        }
        return result;
    }

    /**
     * 删除
     *
     * @param cacheService
     * @param joinPoint
     * @param method
     * @return
     */
    private Object executeDelete(RedisCacheService cacheService, ProceedingJoinPoint joinPoint, Method method) {
        Object result = null;
        //user+session中的用户名(用户登录后将用户的信息保存在了session中)
        String redisKey = cacheService.group()[0] + ":zhangsan";
        try {
            result = joinPoint.proceed();//执行方法....这个就可以看成前置跟后置的分隔符
        } catch (Throwable e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        //后置：将数据库查到的数据保存到Redis
        Long code = redisCache.deleteDataFromRedis(redisKey);
        if (code == 1) {
            LOGGER.info("**********数据成功从redis删除!!!**********");
        }
        return result;
    }

    /**
     * 更新
     *
     * @param cacheService
     * @param joinPoint
     * @param method
     * @return
     */
    private Object executeUpdate(RedisCacheService cacheService, ProceedingJoinPoint joinPoint, Method method) {
        Object result = null;
        //user+session中的用户名(用户登录后将用户的信息保存在了session中)
        String redisKey = cacheService.group()[0] + ":zhangsan";
        User user = (User) joinPoint.getArgs()[0];//,如果是多个参数呢?
        try {
            result = joinPoint.proceed();//执行方法....这个就可以看成前置跟后置的分隔符
        } catch (Throwable e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        //后置：将数据库查到的数据保存到Redis
        String code = redisCache.saveDataToRedis(redisKey, result);
        if (code.equals("OK")) {
            LOGGER.info("**********数据更新成功从Redis缓存!!!**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + result.toString());
        }
        return result;
    }

    /**
     * 查询
     *
     * @param cacheService
     * @param joinPoint
     * @param method
     * @return
     */
    private Object executeDefault(RedisCacheService cacheService, ProceedingJoinPoint joinPoint, Method method) {
        Object result = null;
        //user+session中的用户名(用户登录后将用户的信息保存在了session中)
        String redisKey = cacheService.group()[0] + ":zhangsan";
        //先从redis中查询
        result = redisCache.getDataFromRedis(redisKey);
        if (result != null) {
            LOGGER.info("**********从Redis中查到了数据**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + result.toString());
            return result;
        }
        try {
            result = joinPoint.proceed();//执行方法....这个就可以看成前置跟后置的分隔符
        } catch (Throwable e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        //后置：将数据库查到的数据保存到Redis
        String code = redisCache.saveDataToRedis(redisKey, result);
        if (code.equals("OK")) {
            LOGGER.info("**********数据成功保存到Redis缓存!!!**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + result.toString());
        }
        return result;
    }


    /**
     * 获取拦截的方法对象
     *
     * @param joinPoint
     * @return
     */
    protected Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method;
    }


}
