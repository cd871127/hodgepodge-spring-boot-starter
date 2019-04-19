//package io.github.cd871127.hodgepodge.quartz.job;
//
//import com.alibaba.fastjson.JSONObject;
//import io.github.cd871127.hodgepodge.quartz.exception.IlligalTaskMap;
//import org.quartz.JobKey;
//import org.quartz.TriggerKey;
//
//import java.io.Serializable;
//import java.util.Map;
//
//public class Task implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private static String DEFAULT_GLOBAL_GROUP = null;
//
//    private String taskName;
//    private String taskGroup;
//    private JobKey taskId;
//    private String triggerName;
//    private String triggerGroup;
//    private TriggerKey triggerId;
//    private String cron;
//    private String taskClassName;
//    private Map<String, String> parameter;
//
//    public Task() {
//    }
//
//
//    public String getTaskName() {
//        return taskName;
//    }
//
//    public void setTaskName(String taskName) {
//        this.taskName = taskName;
//    }
//
//    public String getTaskGroup() {
//        return taskGroup;
//    }
//
//    public void setTaskGroup(String taskGroup) {
//        this.taskGroup = taskGroup;
//    }
//
//    public String getCron() {
//        return cron;
//    }
//
//    public void setCron(String cron) {
//        this.cron = cron;
//    }
//
//    public String getTaskClassName() {
//        return taskClassName;
//    }
//
//    public void setTaskClassName(String taskClassName) {
//        this.taskClassName = taskClassName;
//    }
//
//    public JobKey getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(JobKey taskId) {
//        this.taskId = taskId;
//    }
//
//    public String getTriggerName() {
//        return triggerName;
//    }
//
//    public void setTriggerName(String triggerName) {
//        this.triggerName = triggerName;
//    }
//
//    public String getTriggerGroup() {
//        return triggerGroup;
//    }
//
//    public void setTriggerGroup(String triggerGroup) {
//        this.triggerGroup = triggerGroup;
//    }
//
//    public TriggerKey getTriggerId() {
//        return triggerId;
//    }
//
//    public void setTriggerId(TriggerKey triggerId) {
//        this.triggerId = triggerId;
//    }
//
//    public Map<String, String> getParameter() {
//        return parameter;
//    }
//
//    public void setParameter(Map<String, String> parameter) {
//        this.parameter = parameter;
//    }
//
//    public static String getDefaultGlobalGroup() {
//        return DEFAULT_GLOBAL_GROUP;
//    }
//
//    public static void setDefaultGlobalGroup(String defaultGlobalGroup) {
//        DEFAULT_GLOBAL_GROUP = defaultGlobalGroup;
//    }
//
//
//    @SuppressWarnings("unchecked")
//    public static Task buildTaskFromMap(Map<String, String> taskMap) throws IlligalTaskMap {
//        String taskName = taskMap.get("taskName");
//        String taskGroup = taskMap.get("taskGroup");
//        String triggerName = taskMap.get("triggerName");
//        String triggerGroup = taskMap.get("triggerGroup");
//        String cron = taskMap.get("cron");
//        String taskClassName = taskMap.get("taskClassName");
//        String parameter = taskMap.get("parameter");
//        if (taskName == null) {
//            throw new IlligalTaskMap("未设置taskName");
//        }
//        if (triggerName == null) {
//            throw new IlligalTaskMap("未设置triggerName");
//        }
//        if (cron == null) {
//            throw new IlligalTaskMap("未设置cron");
//        }
//        if (taskClassName == null) {
//            throw new IlligalTaskMap("未设置taskClassName");
//        }
//        if (taskGroup == null || triggerGroup == null) {
//            if (DEFAULT_GLOBAL_GROUP == null)
//                throw new IlligalTaskMap("未设置group");
//        }
//
//        Task task = new Task();
//        task.setCron(cron);
//        task.setTaskName(taskName);
//        task.setTaskGroup(taskGroup == null ? DEFAULT_GLOBAL_GROUP : taskGroup);
//        task.setTriggerName(triggerName);
//        task.setTriggerGroup(triggerGroup == null ? DEFAULT_GLOBAL_GROUP : triggerGroup);
//        task.setTaskClassName(taskClassName);
//        task.setTaskId(JobKey.jobKey(task.getTaskName(), task.getTaskGroup()));
//        task.setTriggerId(TriggerKey.triggerKey(task.getTriggerName(), task.getTriggerGroup()));
//
//        if (parameter != null) {
//            JSONObject json = JSONObject.parseObject(parameter);
//            Map<String, String> param = json.toJavaObject(Map.class);
//            task.setParameter(param);
//        }
//        return task;
//    }
//}
