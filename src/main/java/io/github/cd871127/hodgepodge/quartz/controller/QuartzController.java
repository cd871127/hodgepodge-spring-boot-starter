package io.github.cd871127.hodgepodge.quartz.controller;

import io.github.cd871127.hodgepodge.common.util.response.DataList;
import io.github.cd871127.hodgepodge.common.util.response.ResponseCode;
import io.github.cd871127.hodgepodge.common.util.response.ServerResponse;
import io.github.cd871127.hodgepodge.quartz.exception.IlligalTaskMap;
import io.github.cd871127.hodgepodge.quartz.job.Task;
import io.github.cd871127.hodgepodge.quartz.manager.TaskManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

import static io.github.cd871127.hodgepodge.common.util.response.ResponseCode.EMPTY_RESULT;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/quartz/tasks")
@RestController
public class QuartzController {

    @Resource
    private TaskManager taskManager;

    @RequestMapping(value = "", method = POST)
    public ServerResponse addTask(@RequestBody Map<String, String> taskMap) {
        ServerResponse serverResponse = new ServerResponse(ResponseCode.SUCCESS);
        try {
            Task task = Task.buildTaskFromMap(taskMap);
            taskManager.addTask(task);
        } catch (IlligalTaskMap illigalTaskMap) {
            illigalTaskMap.printStackTrace();
            serverResponse.setResponseCode(ResponseCode.ERROR);
            serverResponse.setMsg(illigalTaskMap.getMessage());
        }
        return serverResponse;
    }

    @RequestMapping(value = "{taskGroup}/{taskName}", method = GET)
    public ServerResponse<Task> getTask(@PathVariable String taskGroup, @PathVariable String taskName) {
        Task task = taskManager.getTask(taskName, taskGroup);
        ServerResponse<Task> serverResponse = new ServerResponse<>(ResponseCode.SUCCESS);
        serverResponse.setData(task);
        return serverResponse;
    }

    @RequestMapping(value = "{taskGroup}/{taskName}", method = DELETE)
    public ServerResponse<Task> removeTask(@PathVariable String taskGroup, @PathVariable String taskName) {
        Task task = taskManager.removeTask(taskName, taskGroup);
        ServerResponse<Task> serverResponse = new ServerResponse<>(ResponseCode.SUCCESS);
        serverResponse.setData(task);
        return serverResponse;
    }

    @RequestMapping(value = "", method = GET)
    public ServerResponse<DataList<Task>> getAllTask() {
        if (0 == taskManager.size())
            return new ServerResponse<>(EMPTY_RESULT);
        DataList<Task> dataList = new DataList<>(taskManager.getAllTasks(), taskManager.size());
        ServerResponse<DataList<Task>> serverResponse = new ServerResponse<>(ResponseCode.SUCCESS);
        serverResponse.setData(dataList);
        return serverResponse;
    }

}
