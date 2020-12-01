package com.lzq.rediscurrent.utils.redisComment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lzq
 * @version 1.0
 * @date 2020/11/30 11:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    //有效时间
    int seconds();
    //最大访问次数
    int maxCount();
    //是否需要登录
    boolean needLogin() default true;
}
