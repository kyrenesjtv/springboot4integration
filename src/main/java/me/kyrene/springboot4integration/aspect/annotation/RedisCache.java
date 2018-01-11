package me.kyrene.springboot4integration.aspect.annotation;

import java.lang.annotation.*;

/**
 * Created by wanglin on 2018/1/11.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
}
