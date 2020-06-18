package com.example.guavacache.controller;

import com.example.guavacache.common.Result;
import com.example.guavacache.entity.Entity;
import com.example.guavacache.service.GuavaCacheService;
import com.example.guavacache.service.impl.GuavaCacheFactory;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author ：yaxuSong
 * @Description:
 * @Date: 16:47 2018/12/18
 * @Modified by:
 */
@RestController
@RequestMapping("test")
public class CacheController {

    @Autowired
    private GuavaCacheFactory guavaCacheFactory;

    @Autowired
    private GuavaCacheService<String, String> guavaCacheService;

    private Cache<String, Entity> entityCache = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.SECONDS).maximumSize(2).build();

    @RequestMapping("push")
    public Result<Void> push(String key) {
        Entity entity = new Entity();
        entity.setKey(key);
        entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        entity.setValue(UUID.randomUUID().toString());
        entityCache.put(key, entity);
        return Result.success();
    }

    @RequestMapping("getPush")
    public Result<Entity> getPush(String key) throws Exception {
        Entity value = entityCache.getIfPresent(key);
        return Result.success(value);
    }

    @RequestMapping("get")
    public Result<String> get(String key) throws Exception {
        String value = guavaCacheService.getIfPresent(key);
        return Result.success(value);
    }

    @RequestMapping("put")
    public Result<String> put(String key) {
        guavaCacheService.put(key, "你好，Guava");
        return Result.success();
    }

    @RequestMapping("putByName")
    public Result<String> put1(String key, String name, String value) {
        guavaCacheFactory.buildGuavaCacheService(name).put(key, value);
        return Result.success();
    }

    @RequestMapping("getByName")
    public Result<String> get1(String key, String name) {
        String a = (String) guavaCacheFactory.getGuavaCacheService(name).getIfPresent(key);
        return Result.success(a);
    }
}
