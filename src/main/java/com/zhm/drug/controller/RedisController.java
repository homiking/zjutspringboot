package com.zhm.drug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author kknever
 * @Date 2022/6/12
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(value={"cachekey"}, key = "'rediskey'")
    @GetMapping("/set")
    public Object setKey(){
        return "key is set";
    }
}
