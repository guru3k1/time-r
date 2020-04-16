package com.cga.timer.dao.impl;

import com.cga.timer.dao.TaskDao;
import com.cga.timer.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource("classpath:/DataQueries/sql.properties")
public class TaskDaoImpl implements TaskDao {
    private static final Logger LOG = LoggerFactory.getLogger(TaskDaoImpl.class);

    @Value("${sql.get.task.by.user.id}")
    private String getTasksByUserId;

    @Value("${sql.delete.task}")
    private String deleteTask;



    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TaskDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Task> getTaksByUserId(Long userId) {
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("userId", userId);


        LOG.info("Getting Tasks for user Id [{}]",userId);
        return namedParameterJdbcTemplate.query(getTasksByUserId,parameters,Task.rowMapper());
    }

    @Override
    public int deleteTask(Long taskId) {
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("taskId", taskId);
        return namedParameterJdbcTemplate.update(deleteTask,parameters);
    }

    @Override
    public Long saveTask(Task task) {
        return null;
    }

    @Override
    public Long updateTask(Task task) {
        return null;
    }
}
