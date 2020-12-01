package com.lzq.rediscurrent.utils.redisComment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author lzq
 * @version 1.0
 * @date 2020/11/30 11:29
 */
@Component
public class AccessLimtInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AccessLimtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (null == accessLimit) {
                return true;
            }
            //从注解中获取失效时间
            int seconds = accessLimit.seconds();
            //从注解中获取最大访问次数
            int maxCount = accessLimit.maxCount();
            //从注解中获取是否需要登录
            boolean needLogin = accessLimit.needLogin();
            if (needLogin) {
            //判断是否登录
            }
            //客户端ip地址
            String ip = request.getRemoteAddr();
            String key = ip + ":" + request.getServletPath();
            Integer count = (Integer) redisTemplate.opsForValue().get(key);
            //第一次访问
            if (null == count || -1 == count) {
                redisTemplate.opsForValue().set(key,"1",seconds, TimeUnit.SECONDS);
                return true;
            }
            //如果访问次数<最大次数，则加1操作
            if (count < maxCount) {
                redisTemplate.opsForValue().increment(key, 1);
                return true;
            }
            //超过最大值返回操作频繁
            if (count >= maxCount) {
                System.out.println("count=="+count);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("请求过于频繁，请稍后再试");
                return false;
            }
        }
        return true;
    }

}
