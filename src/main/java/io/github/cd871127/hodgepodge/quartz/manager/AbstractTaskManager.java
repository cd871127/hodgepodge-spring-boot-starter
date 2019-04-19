//package io.github.cd871127.hodgepodge.quartz.manager;
//
//
//import com.alibaba.fastjson.JSONObject;
//import io.github.cd871127.hodgepodge.quartz.job.Task;
//import org.quartz.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//import static org.quartz.CronScheduleBuilder.cronSchedule;
//
//public abstract class AbstractTaskManager implements TaskManager {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    private Scheduler scheduler;
//
//    public void setScheduler(Scheduler scheduler) {
//        this.scheduler = scheduler;
//    }
//
//    public AbstractTaskManager() {
//        logger.info("Init task manager.");
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public void addTask(Task task) {
//        logger.info("Add task: {}", JSONObject.toJSONString(task));
//        try {
//            JobBuilder jobBuilder = JobBuilder.newJob((Class<? extends Job>) Class.forName(task.getTaskClassName())).withIdentity(task.getTaskId());
//            if (task.getParameter() != null)
//                jobBuilder = jobBuilder.usingJobData(new JobDataMap(task.getParameter()));
//            JobDetail job = jobBuilder.build();
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getTriggerId()).startNow()
//                    .withSchedule(cronSchedule(task.getCron())).build();
//            scheduler.scheduleJob(job, trigger);
//            _addTask(task);
//            if (scheduler.isShutdown())
//                scheduler.start();
//        } catch (SchedulerException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Task getTask(String taskName, String taskGroup) {
//        logger.debug("Get task: [{}, {}]", taskName, taskGroup);
//        return _getTask(taskName, taskGroup);
//    }
//
//    @Override
//    public Task removeTask(String taskName, String taskGroup) {
//        Task task = _removeTask(taskName, taskGroup);
//        try {
//            scheduler.pauseTrigger(task.getTriggerId());
//            scheduler.unscheduleJob(task.getTriggerId());
//            scheduler.deleteJob(task.getTaskId());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//        logger.info("Remove task: {}", JSONObject.toJSONString(task));
//        return task;
//    }
//
//    @Override
//    public List<Task> getAllTasks() {
//        return _getAllTasks();
//    }
//
//    abstract protected void _addTask(Task task);
//
//    abstract protected Task _getTask(String taskName, String taskGroup);
//
//    abstract protected Task _removeTask(String taskName, String taskGroup);
//
//    abstract protected List<Task> _getAllTasks();
//
//    String getGroup(String group) {
//        return group == null ? Task.getDefaultGlobalGroup() : group;
//    }
//}
