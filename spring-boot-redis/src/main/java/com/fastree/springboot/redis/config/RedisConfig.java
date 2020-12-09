package com.fastree.springboot.redis.config;

import com.fastree.springboot.redis.listener.UserMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

/*
RedisAutoConfiguration
    ==> LettuceConnectionConfiguration extends RedisConnectionConfiguration
        ==> DefaultClientResources
        ==> LettuceConnectionFactory

RedisRepositoriesAutoConfiguration
    ==> RedisRepositoriesRegistrar extends AbstractRepositoryConfigurationSourceSupport
        ==> void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {}

@Autowired
HashOperations<String, byte[], byte[]> hashOperations; //和HashMapper配合使用

HashMapper
    ==> ObjectHashMapper
    ==> BeanUtilsHashMapper
    ==> Jackson2HashMapper
 */
@Configuration
@EnableRedisRepositories(basePackages = {"com.fastree.springboot.redis.dao"})
public class RedisConfig {

    private final UserMessageListener messageListener;

    public RedisConfig(UserMessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer messageListenerContainer = new RedisMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.addMessageListener(messageListener, Arrays.asList(new ChannelTopic("topic1")));
        return messageListenerContainer;
    }

}
