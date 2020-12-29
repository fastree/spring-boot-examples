package com.fastree.springboot.flowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FlowableApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FlowableApplication.class, args);

    }
}
