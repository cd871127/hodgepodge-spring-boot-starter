package io.github.cd871127.hodgepodge.quartz.service.impl;

import io.github.cd871127.hodgepodge.quartz.manager.TaskManager;
import io.github.cd871127.hodgepodge.quartz.service.QuartzService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class QuartzServiceImpl<T extends TaskManager> implements QuartzService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QuartzServiceImpl(T taskManager) {
        this.taskManager = taskManager;
    }

    private T taskManager;

    private boolean staticTaskStatus = false;

    @Resource
    private Scheduler scheduler;

    @SuppressWarnings("unchecked")
    @Override
    public void startStaticTasks() {
        if (!staticTaskStatus) {
            staticTaskStatus = true;
            logger.info("Start static tasks, total {} tasks.", taskManager.size());
            taskManager.getAllTasks().forEach((task) -> {
                try {
                    JobBuilder jobBuilder = JobBuilder.newJob((Class<? extends Job>) Class.forName(task.getTaskClassName())).withIdentity(task.getTaskId());
                    if (task.getParameter() != null)
                        jobBuilder = jobBuilder.usingJobData(new JobDataMap(task.getParameter()));
                    JobDetail job = jobBuilder.build();
                    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getTriggerId()).startNow()
                            .withSchedule(cronSchedule(task.getCron())).build();
                    scheduler.scheduleJob(job, trigger);
                    if (scheduler.isShutdown())
                        scheduler.start();
                } catch (SchedulerException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } else {
            logger.info("Static task already started.");
        }
    }
}
