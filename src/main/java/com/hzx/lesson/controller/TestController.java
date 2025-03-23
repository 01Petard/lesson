package com.hzx.lesson.controller;

import com.hzx.lesson.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 仅用于测试
 * @author 15203
 */
@RestController
@RequestMapping("/test/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TestController {

    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;
    // 统一测试键的前缀，便于后续清理（可选）
    String testPrefix = "test_";

    @GetMapping("/redis/delete")
    public Result<String> redisDelete() {
        // 获取所有以 prefix 开头的键并删除
        Set<String> keys = redisTemplate.keys(testPrefix + "*");
        if (ObjectUtils.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
            log.info("Cleaned up {} test keys", keys.size());
        }
        return Result.success("清理所有测试键");
    }

    @GetMapping("/redis/add")
    public Result<String> redisAdd() {

        try {
            // 1. 字符串操作
            testStringOperations(testPrefix);

            // 2. 哈希操作
            testHashOperations(testPrefix);

            // 3. 列表操作
            testListOperations(testPrefix);

            // 4. 集合操作
            testSetOperations(testPrefix);

            // 5. 有序集合操作
            testZsetOperations(testPrefix);

            // 6. 过期时间（已包含在其他测试中）
            return Result.success("Redis test successful");
        } catch (Exception e) {
            log.error("Redis test failed: ", e);
            return Result.error("Redis test failed");
        }
    }

    // --- 字符串操作 ---
    private void testStringOperations(String prefix) {
        String key = prefix + "stringTestKey";
        String value = "Hello Redis!";

        // 设置键并指定 TTL（10秒）
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        log.info("Set string value: {}", value);

        Object result = redisTemplate.opsForValue().get(key);
        log.info("Get string value: {}", result);

        // 追加内容
        redisTemplate.opsForValue().append(key, " Appended!");
        String appendedValue = (String) redisTemplate.opsForValue().get(key);
        log.info("Appended string value: {}", appendedValue);

        // 获取子字符串
        String substring = (String) redisTemplate.opsForValue().get(key, 0, 5);
        log.info("Substring (0-5): {}", substring);

        // 设置过期时间（覆盖之前的 TTL）
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);
        log.info("Set expiration for {} (10 seconds)", key);
    }

    // --- 哈希操作 ---
    private void testHashOperations(String prefix) {
        String hashKey = prefix + "hashTestKey";
        // 设置键并指定 TTL（10秒）
        redisTemplate.expire(hashKey, 10, TimeUnit.SECONDS);

        // 初始化哈希键（确保键存在）
        redisTemplate.opsForHash().put(hashKey, "field1", "value1");
        redisTemplate.opsForHash().put(hashKey, "field2", 123);

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(hashKey);
        log.info("Hash entries: {}", entries);

        // 批量设置
        Map<String, Object> batchEntries = new HashMap<>();
        batchEntries.put("field3", "value3");
        batchEntries.put("field4", true);
        redisTemplate.opsForHash().putAll(hashKey, batchEntries);

        // 删除字段
        redisTemplate.opsForHash().delete(hashKey, "field2");
        log.info("Hash after deletion: {}", redisTemplate.opsForHash().entries(hashKey));
    }

    // --- 列表操作 ---
    private void testListOperations(String prefix) {
        String listKey = prefix + "listTestKey";
        // 设置键并指定 TTL（10秒）
        redisTemplate.expire(listKey, 10, TimeUnit.SECONDS);

        redisTemplate.opsForList().rightPush(listKey, "item1");
        redisTemplate.opsForList().leftPush(listKey, "item2");

        Long size = redisTemplate.opsForList().size(listKey);
        log.info("List size: {}", size);

        List<Object> elements = redisTemplate.opsForList().range(listKey, 0, -1);
        log.info("List elements: {}", elements);

        // 右弹出
        Object popped = redisTemplate.opsForList().rightPop(listKey);
        log.info("Popped element: {}", popped);
    }

    // --- 集合操作 ---
    private void testSetOperations(String prefix) {
        String setKey = prefix + "setTestKey";
        // 设置键并指定 TTL（10秒）
        redisTemplate.expire(setKey, 10, TimeUnit.SECONDS);

        redisTemplate.opsForSet().add(setKey, "member1", "member2", "member3");

        Set<Object> members = redisTemplate.opsForSet().members(setKey);
        log.info("Set members: {}", members);

        // 删除元素
        Long removed = redisTemplate.opsForSet().remove(setKey, "member2");
        log.info("Removed members: {}", removed);
    }

    // --- 有序集合操作 ---
    private void testZsetOperations(String prefix) {
        String zSetKey = prefix + "zSetTestKey";
        // 设置键并指定 TTL（10秒）
        redisTemplate.expire(zSetKey, 10, TimeUnit.SECONDS);

        redisTemplate.opsForZSet().add(zSetKey, "member1", 1.0);
        redisTemplate.opsForZSet().add(zSetKey, "member2", 2.0);

        Set<Object> members = redisTemplate.opsForZSet().range(zSetKey, 0, -1);
        log.info("Sorted set members: {}", members);

        Double score = redisTemplate.opsForZSet().score(zSetKey, "member1");
        log.info("Score of member1: " + score);
    }


    /**
     * 测试arthas的接口响应时间
     * @return
     */
    @GetMapping("/arthas")
    public Result<String> test() {
        this.m1();
        this.m2();
        this.m3();
        return Result.success("hello, arthas!");
    }

    private void m1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void m2() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void m3() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}