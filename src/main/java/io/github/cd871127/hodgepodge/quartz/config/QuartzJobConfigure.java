package io.github.cd871127.hodgepodge.quartz.config;

import io.github.cd871127.hodgepodge.quartz.JobInfo;
import io.github.cd871127.hodgepodge.quartz.context.QuartzJobStartupListener;
import io.github.cd871127.hodgepodge.quartz.properties.QuartzJobProperties;
import io.github.cd871127.hodgepodge.quartz.service.QuartzJobService;
import org.quartz.JobKey;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(QuartzJobProperties.class)
@ConditionalOnProperty(prefix = "io.github.cd871127.hodgepodge.quartz", value = "enable", havingValue = "true")
public class QuartzJobConfigure {

    @Resource
    private QuartzJobProperties quartzJobProperties;

    @Bean
    @ConditionalOnMissingBean
    public QuartzJobService quartzJobService() {
        //转换list到map
        Map<JobKey, JobInfo> jobsMap = new HashMap<>();
        quartzJobProperties.getJobs().forEach((v) -> {
            JobInfo jobInfo = JobInfo.buildFromMap(v);
            jobsMap.put(jobInfo.getJobKey(), jobInfo);
        });
        return new QuartzJobService(jobsMap);
    }

    @Bean
    @ConditionalOnMissingBean
    public QuartzJobStartupListener quartzJobStartupListener() {
        return new QuartzJobStartupListener();
    }
}
