package io.github.cd871127.hodgepodge.quartz.context;

import io.github.cd871127.hodgepodge.quartz.service.QuartzJobService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

/**
 * app启动时,启动所有在配置文件中的job
 */
public class QuartzJobStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private QuartzJobService quartzJobService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        quartzJobService.startStaticJobs();
    }
}
