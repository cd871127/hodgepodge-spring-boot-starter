package io.github.cd871127.hodgepodge.quartz.service;

import io.github.cd871127.hodgepodge.quartz.JobInfo;
import org.quartz.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class QuartzJobService {

    public static final String DEFAULT_GROUP = "com.paic.amc";

    @Resource
    private Scheduler scheduler;

    public QuartzJobService(Map<JobKey, JobInfo> jobContainer) {
        setJobContainer(jobContainer);
    }

    public void setJobContainer(Map<JobKey, JobInfo> jobContainer) {
        this.jobContainer = jobContainer;
    }

    private Map<JobKey, JobInfo> jobContainer;

    /**
     * 启动所有配置文件中的job
     */
    @SuppressWarnings("unchecked")
    public void startStaticJobs() {
        if (null == jobContainer || jobContainer.size() == 0)
            return;
        jobContainer.forEach((k, v) -> {
            try {
                addJob(v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 添加job
     *
     * @param jobKey       job标识 默认组为DEFAULT
     * @param jobClassName 具体job类的完全限定名
     * @param triggerKey   trigger标识
     * @param cron         执行时间
     * @throws ClassNotFoundException 找不到job类
     */
    @SuppressWarnings("unchecked")
    public void addJob(JobKey jobKey, String jobClassName, TriggerKey triggerKey, String cron, Map<String, String> parameter) throws ClassNotFoundException {
        JobBuilder jobBuilder = JobBuilder.newJob((Class<? extends Job>) Class.forName(jobClassName)).withIdentity(jobKey);
        if (parameter != null)
            jobBuilder = jobBuilder.usingJobData(new JobDataMap(parameter));
        JobDetail job = jobBuilder.build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow()
                .withSchedule(cronSchedule(cron)).build();
        try {
            scheduler.scheduleJob(job, trigger);
            if (scheduler.isShutdown())
                scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void addJob(JobInfo jobInfo) throws ClassNotFoundException {
        addJob(jobInfo.getJobKey(), jobInfo.getJobClass(), jobInfo.getTriggerKey(), jobInfo.getCron(), jobInfo.getParameter());
    }


    /**
     * @return 所有job及关联的trigger的信息
     */
    public List<JobInfo> getAllJobInfo() {
//        List<JobInfo> jobInfoList = new ArrayList<>();
//        try {
//            List<String> groupNames = scheduler.getJobGroupNames();
//            for (String groupName : groupNames) {
//                Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher.groupEquals(groupName));
//                for (JobKey jobKey : jobKeySet) {
//                    List<? extends Trigger> triggerList = scheduler.getTriggersOfJob(jobKey);
//                    List<TriggerKey> triggerKeyList = new ArrayList<>();
//                    for (Trigger trigger : triggerList) {
//                        triggerKeyList.add(trigger.getKey());
//                    }
//                    JobInfo jobInfo = new JobInfo(jobKey, triggerKeyList);
//                    jobInfoList.add(jobInfo);
//                }
//            }
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//        return jobInfoList;
        return null;
    }

}
