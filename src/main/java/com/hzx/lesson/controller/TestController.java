package com.hzx.lesson.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 仅用于测试
 * @author 15203
 */
@RestController
public class TestController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试redis的连通性
     * @return
     */
    @GetMapping("/redis")
    public String testRedis() {
        // 测试 RedisTemplate
        redisTemplate.opsForValue().set("testKey", "testValue");
        Object value = redisTemplate.opsForValue().get("testKey");
        System.out.println("RedisTemplate Value: " + value);

        // 测试 StringRedisTemplate
        stringRedisTemplate.opsForValue().set("stringTestKey", "stringTestValue");
        String stringValue = stringRedisTemplate.opsForValue().get("stringTestKey");
        System.out.println("StringRedisTemplate Value: " + stringValue);

        return "Redis test successful";
    }

    /**
     * 测试arthas的接口响应时间
     * @return
     */
    @GetMapping("/arthas")
    public String test() {
        this.m1();
        this.m2();
        this.m3();
        return "hello, arthas!";
    }

    private void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void m2(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void m3(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}