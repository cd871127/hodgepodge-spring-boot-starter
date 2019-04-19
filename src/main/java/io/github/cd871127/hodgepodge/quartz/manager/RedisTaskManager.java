//package io.github.cd871127.hodgepodge.quartz.manager;
//
//import io.github.cd871127.hodgepodge.quartz.job.Task;
//import org.quartz.JobKey;
//import org.springframework.data.redis.core.BoundHashOperations;
//
//import java.util.List;
//
//public class RedisTaskManager extends AbstractTaskManager {
//
//    public void setRedisCache(BoundHashOperations<String, JobKey, Task> redisCache) {
//        this.redisCache = redisCache;
//    }
//
//    private BoundHashOperations<String, JobKey, Task> redisCache;
//
//
//    @Override
//    protected void _addTask(Task task) {
//        redisCache.put(task.getTaskId(), task);
//    }
//
//    @Override
//    protected Task _getTask(String taskName, String taskGroup) {
//        JobKey jobKey = JobKey.jobKey(taskName, getGroup(taskGroup));
//        return redisCache.get(jobKey);
//    }
//
//    @Override
//    protected Task _removeTask(String taskName, String taskGroup) {
//        JobKey jobKey = JobKey.jobKey(taskName, getGroup(taskGroup));
//        Task task = redisCache.get(jobKey);
//        redisCache.delete(jobKey);
//        return task;
//    }
//
//    @Override
//    protected List<Task> _getAllTasks() {
//        return redisCache.values();
//    }
//
//    @Override
//    public int size() {
//        return Math.toIntExact(redisCache.size());
//    }
//}
