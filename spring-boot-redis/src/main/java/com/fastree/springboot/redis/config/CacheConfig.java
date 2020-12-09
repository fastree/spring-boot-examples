package com.fastree.springboot.redis.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

/*
CacheAutoConfiguration
    ==> CacheConfigurationImportSelector implements ImportSelector
    ==> CacheManagerCustomizers
    ==> CacheManagerValidator
 */
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            builder.withCacheConfiguration("cache1", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                    .withCacheConfiguration("cache2", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(20)));
        };
    }
}
