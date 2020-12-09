package com.fastree.springboot.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
SessionAutoConfiguration
    ==> ServletSessionConfiguration
        ==> ServletSessionRepositoryValidator
        ==> SessionRepositoryFilterConfiguration
            ==> SessionRepositoryFilter
                ==> filterChain.doFilter(SessionRepositoryRequestWrapper, SessionRepositoryResponseWrapper);
                ==> SessionRepository
                    ==> FindByIndexNameSessionRepository
                        ==> RedisIndexedSessionRepository
                ==> HttpSessionIdResolver
        ==> DefaultCookieSerializer
 */
@Configuration
@EnableRedisHttpSession /*that is equivalent to (spring.session.store-type=redis)*/
public class SessionConfig implements BeanClassLoaderAware {
    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /*
    REST风格：根据请求头验证
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }
    */

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(SecurityJackson2Modules.getModules(this.classLoader));
        return objectMapper;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer(objectMapper());
    }


    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                System.out.println(se.getSession().getId() + " sessionCreated ....");

            }
            @Override
            public void sessionDestroyed(HttpSessionEvent se) {
                System.out.println(se.getSession().getId() + " sessionDestroyed ....");
            }
        };
    }

}
