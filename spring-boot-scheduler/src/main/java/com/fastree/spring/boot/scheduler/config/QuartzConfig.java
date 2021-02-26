package com.fastree.spring.boot.scheduler.config;

import com.fastree.spring.boot.scheduler.listener.TaskJobListener;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {

    @QuartzDataSource
    @Bean
    public DataSource quartzDataSource() {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/scheduler?useSSL=false&serverTimezone=Asia/Shanghai");
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setUsername("root");
        configuration.setPassword("123456");
        DataSource dataSource = new HikariDataSource(configuration);
        return dataSource;
    }

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return schedulerFactoryBean -> {
//            schedulerFactoryBean.setSchedulerName("mySchedulerX");
//            schedulerFactoryBean.setTaskExecutor(Executors.newFixedThreadPool(10));

            // 这里可以注册全局的job，trigger，scheduler监听器
            schedulerFactoryBean.setGlobalJobListeners(new TaskJobListener());
        };
    }
}
