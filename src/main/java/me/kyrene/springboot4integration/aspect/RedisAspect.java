package me.kyrene.springboot4integration.aspect;

import me.kyrene.springboot4integration.cache.redisCache.RedisCache;
import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.utils.serialize.SerializeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * aop 实现redis缓存处理
 * Created by wanglin on 2018/1/11.
 */
@Component
@Aspect
public class RedisAspect {
    private static final Logger LOGGER  = LoggerFactory.getLogger(RedisAspect.class);

    @Autowired
    @Qualifier("redisCache")//如果RedisCache存在多个,就去找定义的redisCache
    private RedisCache redisCache;

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut(value = "@annotation(me.kyrene.springboot4integration.aspect.annotation.RedisCache)")//定义的注解的类的全路径
    public void pointcutMethod(){

    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint){
        //前置：从Redis里获取缓存
        //先获取目标方法参数
        long startTime = System.currentTimeMillis();
        String applId = null;//参数的value
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0]);
        }

        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        //redis中key格式：    applId:方法名称
        String redisKey = applId + ":" + className + "." + methodName;

        Object obj = redisCache.getDataFromRedis(redisKey);

        if(obj!=null){
            LOGGER.info("**********从Redis中查到了数据**********");
            LOGGER.info("Redis的KEY值:"+redisKey);
            LOGGER.info("REDIS的VALUE值:"+obj.toString());
            return obj;
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("Redis缓存AOP处理所用时间:"+(endTime-startTime));
        LOGGER.info("**********没有从Redis查到数据**********");
        try{
            obj = joinPoint.proceed();//执行方法....这个就可以看成前置跟后置的分隔符
        }catch(Throwable e){
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("**********开始从MySQL查询数据**********");
        //后置：将数据库查到的数据保存到Redis
        String code = redisCache.saveDataToRedis(redisKey,obj);
        if(code.equals("OK")){
            LOGGER.info("**********数据成功保存到Redis缓存!!!**********");
            LOGGER.info("Redis的KEY值:"+redisKey);
            LOGGER.info("REDIS的VALUE值:"+obj.toString());
        }
        return obj;
    }


}
