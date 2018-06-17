package io.github.cd871127.hodgepodge.quartz.autoconfigure;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cd871127.hodgepodge.quartz.exception.IlligalTaskMap;
import io.github.cd871127.hodgepodge.quartz.job.Task;
import io.github.cd871127.hodgepodge.quartz.manager.MemoryTaskManager;
import io.github.cd871127.hodgepodge.quartz.manager.RedisTaskManager;
import io.github.cd871127.hodgepodge.quartz.manager.TaskManager;
import io.github.cd871127.hodgepodge.quartz.properties.StaticTasksProperties;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(StaticTasksProperties.class)
@ConditionalOnProperty(prefix = "hodgepodge.quartz", value = "enable", havingValue = "true")
@ComponentScan("io.github.cd871127.hodgepodge.quartz.controller")
public class QuartzAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StaticTasksProperties staticTasksProperties;

    @Value("${hodgepodge.quartz.default-global-group:DEFAULT_GLOBAL_GROUP}")
    private String defaultGlobalGroup;

    @Value("${hodgepodge.quartz.redis.cache-name:hodgepodge.quartz}")
    private String redisCacheName;

    @Bean
    @ConditionalOnProperty(prefix = "hodgepodge.quartz", value = "taskCacheType", havingValue = "memory", matchIfMissing = true)
    public MemoryTaskManager memoryTaskManager(Scheduler scheduler) {
        MemoryTaskManager memoryTaskManager = new MemoryTaskManager();
        memoryTaskManager.setScheduler(scheduler);
        initTaskManager(memoryTaskManager);
        return memoryTaskManager;
    }

    @SuppressWarnings("unchecked")
    @Bean
    @ConditionalOnProperty(prefix = "hodgepodge.quartz", value = "taskCacheType", havingValue = "redis")
    public RedisTaskManager redisTaskManager(RedisTemplate redisTemplate, Scheduler scheduler) {
        RedisTaskManager redisTaskManager = new RedisTaskManager();
        redisTaskManager.setScheduler(scheduler);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);

        BoundHashOperations redisCache = redisTemplate.boundHashOps(redisCacheName);
        //清空这个hash table
        redisTemplate.delete(redisCacheName);
        redisTaskManager.setRedisCache(redisCache);
        initTaskManager(redisTaskManager);
        return redisTaskManager;
    }

//    @Bean
//    @ConditionalOnBean(RedisTaskManager.class)
//    public QuartzService RedisQuartzService(RedisTaskManager redisTaskManager) {
//        return new QuartzServiceImpl<>(redisTaskManager);
//    }

    //    @Bean
//    @ConditionalOnProperty(prefix = "hodgepodge.quartz", value = "taskCacheType", havingValue = "database")
//    public QuartzService DatabaseQuartzService() {
//        return new QuartzServiceImpl<>(memoryTaskManager());
//    }


//    @Bean
//    @ConditionalOnProperty(prefix = "spring.quartz", value = "job-store-type", havingValue = "memory", matchIfMissing = true)
//    public MemoryTaskManager memoryTaskManager() {
//
//        return memoryTaskManager;
//    }

    private void initTaskManager(TaskManager taskManager) {
        Task.setDefaultGlobalGroup(defaultGlobalGroup);
        if (staticTasksProperties.getTasks() != null) {
            staticTasksProperties.getTasks().forEach((v) -> {
                try {
                    taskManager.addTask(Task.buildTaskFromMap(v));
                } catch (IlligalTaskMap illigalTaskMap) {
                    illigalTaskMap.printStackTrace();
                }
            });
        }
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public QuartzJobStartupListener quartzJobStartupListener() {
//        return new QuartzJobStartupListener();
//    }
}
