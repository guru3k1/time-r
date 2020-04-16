package com.cga.timer.handler;

import com.cga.timer.model.Task;
import com.cga.timer.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping ("api")
public class TimeController {
    private static final Logger LOG = LoggerFactory.getLogger(TimeController.class);

    protected TaskService taskService;

    public TimeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value="/getTasks", produces = "application/json")
    public List<Task> getTasksByUser(@RequestParam Long userId){
        LOG.info("Get tasks by user [{}]",userId);
        return taskService.getTasksByUserId(userId);
    }

    @PostMapping(value="/deleteTask", produces = "application/json")
    public String deleteTask(@RequestParam Long taskId){
        LOG.info("Delete task with id [{}]",taskId);
        return taskService.deleteTask(taskId);
    }
}
