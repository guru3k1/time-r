package com.cga.timer.dao;

import com.cga.timer.model.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getTaksByUserId(Long userId);
    int deleteTask(Long taskId);
    Long saveTask(Task task);
    Long updateTask(Task task);
}
