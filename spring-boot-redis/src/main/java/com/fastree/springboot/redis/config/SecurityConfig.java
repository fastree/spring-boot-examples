package com.fastree.springboot.redis.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {

    private final FindByIndexNameSessionRepository<S> sessionRepository;

    public SecurityConfig(FindByIndexNameSessionRepository<S> sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((authorize) -> {
                    authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin.permitAll();
                })
                .rememberMe(rememberMe -> {
                    rememberMe.rememberMeServices(rememberMeServices());
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.maximumSessions(1)
                            .sessionRegistry(sessionRegistry());
                });
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }

}
