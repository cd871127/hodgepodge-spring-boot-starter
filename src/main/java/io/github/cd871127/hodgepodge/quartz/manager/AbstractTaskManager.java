package io.github.cd871127.hodgepodge.quartz.manager;


import com.alibaba.fastjson.JSONObject;
import io.github.cd871127.hodgepodge.quartz.job.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractTaskManager implements TaskManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractTaskManager() {
        logger.info("Init task manager.");
    }

    @Override
    public void addTask(Task task) {
        logger.info("Add task: {}",JSONObject.toJSONString(task));
        _addTask(task);
    }

    @Override
    public Task getTask(String taskName, String taskGroup) {
        logger.debug("Get task: [{}, {}]",taskName,taskGroup);
        return _getTask(taskName, taskGroup);
    }

    @Override
    public Task removeTask(String taskName, String taskGroup) {
        Task task=_removeTask(taskName, taskGroup);
        logger.info("Remove task: {}",JSONObject.toJSONString(task));
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return _getAllTasks();
    }


    abstract protected void _addTask(Task task);

    abstract protected Task _getTask(String taskName, String taskGroup);

    abstract protected Task _removeTask(String taskName, String taskGroup);

    abstract protected List<Task> _getAllTasks();

    String getGroup(String group) {
        return group == null ? Task.getDefaultGlobalGroup() : group;
    }
}
