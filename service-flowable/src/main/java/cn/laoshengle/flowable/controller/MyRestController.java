package cn.laoshengle.flowable.controller;

import org.flowable.task.api.Task;
import cn.laoshengle.flowable.pojo.TaskRepresentation;
import cn.laoshengle.flowable.service.MyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/06/16 21:06:10
 **/

@RestController
public class MyRestController {

    @Resource
    private MyService myService;

    /**
     * 启动process
     */
    @PostMapping(value="/process")
    public void startProcessInstance() {
        myService.startProcess();
    }

    /**
     * 查询需要执行的任务tasks
     * @param assignee
     * @return
     */
    @GetMapping(value="/tasks", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }
}
