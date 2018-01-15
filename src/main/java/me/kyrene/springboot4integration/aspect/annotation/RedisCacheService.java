package me.kyrene.springboot4integration.aspect.annotation;

import java.lang.annotation.*;

/**
 * Created by wanglin on 2018/1/11.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheService {
    enum CACHE_OPERATION {
        GET,//查询操作
        DELETE,//删除操作
        UPDATE,//更新操作
        INSERT;//插入操作
    }

    /**
     * 存储的分组
     **/
    String[] group();

    /**
     * 当前缓存操作类型  默认是查找
     **/
    CACHE_OPERATION cacheOperation() default CACHE_OPERATION.GET;

    /**
     * 存储的Key 默认加入类名跟方法名
     **/
    String key() default "";

    /**
     * 是否使用缓存
     */
    boolean use() default true;

}
