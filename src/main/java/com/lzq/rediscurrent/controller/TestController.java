package com.lzq.rediscurrent.controller;

import com.lzq.rediscurrent.utils.redisComment.AccessLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzq
 * @version 1.0
 * @date 2020/11/30 11:27
 */
@Controller
@RequestMapping("access")
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;
    @ResponseBody
    @GetMapping("accessLimit")
    @AccessLimit(seconds = 1, maxCount = 2,needLogin = true)
    public String accessLimit() {
        return "it is ok";
    }
}
