package io.github.cd871127.hodgepodge.quartz;

import io.github.cd871127.hodgepodge.quartz.exception.JobInfoException;
import io.github.cd871127.hodgepodge.quartz.service.QuartzJobService;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import java.util.Map;

/**
 * job基本信息
 */
public class JobInfo {

    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private String cron;
    private String jobClass;
    private Map<String, String> parameter;

    private JobInfo() {
    }

    @SuppressWarnings("unchecked")
    public static JobInfo buildFromMap(Map<String, Object> jobMap) throws JobInfoException {
        JobInfo jobInfo = new JobInfo();
        String jobName = jobMap.get("jobName") == null ? null : jobMap.get("jobName").toString();
        String jobGroup = jobMap.getOrDefault("jobGroup", QuartzJobService.DEFAULT_GROUP).toString();
        String triggerName = jobMap.getOrDefault("triggerName", jobName + "Trigger").toString();
        String triggerGroup = jobMap.getOrDefault("triggerGroup", QuartzJobService.DEFAULT_GROUP).toString();
        String cron = jobMap.get("cron") == null ? null : jobMap.get("cron").toString();
        String jobClass = jobMap.get("jobClass") == null ? null : jobMap.get("jobClass").toString();


        String errorMessage = null;
        if (jobName == null) {
            errorMessage = "job名称为空";
        } else if (cron == null) {
            errorMessage = "cron表达式为空";
        } else if (jobClass == null) {
            errorMessage = "jobClass为空";
        }
        if (null != errorMessage)
            throw new JobInfoException(errorMessage);
        jobInfo.setJobName(jobName);
        jobInfo.setJobGroup(jobGroup);
        jobInfo.setTriggerName(triggerName);
        jobInfo.setTriggerGroup(triggerGroup);
        jobInfo.setCron(cron);
        jobInfo.setJobClass(jobClass);
        jobInfo.setParameter((Map<String, String>) jobMap.get("parameter"));

        return jobInfo;
    }

    public JobKey getJobKey() {
        return JobKey.jobKey(getJobName(), getTriggerGroup());
    }

    public TriggerKey getTriggerKey() {
        return TriggerKey.triggerKey(getTriggerName(), getTriggerGroup());
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }
}
