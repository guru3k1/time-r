package com.cga.timer.handler;

import com.cga.timer.model.RangeDates;
import com.cga.timer.model.Task;
import com.cga.timer.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value="/addRange", produces = "application/json")
    public String addRange(@RequestBody RangeDates rangeDates, HttpServletRequest request){
        LOG.info("AddRange to taskId [{}]",rangeDates.getTaskId());
        return taskService.addRange(rangeDates);
    }

    @PostMapping(value="/closeRange", produces = "application/json")
    public String closeRange(@RequestParam Long rangeId){
        LOG.info("Delete task with id [{}]",rangeId);
        return taskService.closeRange(rangeId);
    }

    @PostMapping(value="/addTask", produces = "application/json")
    public String addTask(@RequestBody Task task){
        LOG.info("Add Task of userId [{}]",task.getUserId());
        return taskService.addTask(task);
    }

    @PostMapping(value="/closeTask", produces = "application/json")
    public String closeTask(@RequestParam Long taskId){
        LOG.info("Close task with id [{}]",taskId);
        return taskService.closeTask(taskId);
    }
}
