package com.lzq.rediscurrent.utils.redisComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lzq
 * @version 1.0
 * @date 2020/11/30 11:38
 */
@Configuration
public class IntercepterConfig  implements WebMvcConfigurer {
    @Autowired
    private AccessLimtInterceptor accessLimtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimtInterceptor)
                //配置拦截路径
                .addPathPatterns("/access/accessLimit")
                //配置不拦截路径
                .excludePathPatterns("/access/login");
    }

}
