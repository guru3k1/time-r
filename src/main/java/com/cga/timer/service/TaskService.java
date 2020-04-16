package com.cga.timer.service;

import com.cga.timer.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasksByUserId (Long userId);
    String deleteTask(Long taskId);
    Task saveTask(Task task);
    Task updateTask(Task task);
}
