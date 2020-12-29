package com.fastree.springboot.flowable.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowableConfig {

//    @Bean
    public EngineConfigurationConfigurer customEngineConfigurationConfigurer() {
        return new EngineConfigurationConfigurer<SpringProcessEngineConfiguration>() {
            @Override
            public void configure(SpringProcessEngineConfiguration engineConfiguration) {
            }
        };
    }
}
