//package io.github.cd871127.hodgepodge.quartz.manager;
//
//import io.github.cd871127.hodgepodge.quartz.job.Task;
//import org.quartz.JobKey;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MemoryTaskManager extends AbstractTaskManager {
//
//    private Map<JobKey, Task> taskContainer = new HashMap<>();
//
//    @Override
//    protected void _addTask(Task task) {
//        taskContainer.put(task.getTaskId(), task);
//    }
//
//    @Override
//    protected Task _getTask(String taskName, String taskGroup) {
//        return taskContainer.get(JobKey.jobKey(taskName, getGroup(taskGroup)));
//    }
//
//    @Override
//    protected Task _removeTask(String taskName, String taskGroup) {
//        JobKey jobKey = JobKey.jobKey(taskName, getGroup(taskGroup));
//        Task task = taskContainer.get(jobKey);
//        taskContainer.remove(jobKey);
//        return task;
//    }
//
//    @Override
//    protected List<Task> _getAllTasks() {
//        List<Task> tasks = new ArrayList<>(taskContainer.size());
//        taskContainer.forEach((k, v) -> tasks.add(v));
//        return tasks;
//    }
//
//
//    @Override
//    public int size() {
//        return taskContainer.size();
//    }
//}
