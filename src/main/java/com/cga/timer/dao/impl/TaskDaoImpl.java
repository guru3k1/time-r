package com.cga.timer.dao.impl;

import com.cga.timer.dao.TaskDao;
import com.cga.timer.model.RangeDates;
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

    @Value("${sql.add.range}")
    private String addRange;

    @Value("${sql.close.range}")
    private String closeRange;

    @Value("${sql.add.task}")
    private String addTask;

    @Value("${sql.close.task}")
    private String closeTask;


    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TaskDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Task> getTaksByUserId(Long userId) {
        LOG.info("Getting Tasks for user Id [{}]",userId);
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return namedParameterJdbcTemplate.query(getTasksByUserId,parameters,Task.rowMapper());
    }

    @Override
    public int deleteTask(Long taskId) {
        LOG.info("Deleting Task with task Id [{}]",taskId);
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

    @Override
    public int addRange(RangeDates rangeDates) {
        LOG.info("Adding range with task Id [{}]",rangeDates.getTaskId());
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("taskId", rangeDates.getTaskId());
        parameters.put("userId", rangeDates.getUserId());
        return namedParameterJdbcTemplate.update(addRange,parameters);
    }

    @Override
    public int closeRange(Long rangeId) {
        LOG.info("Closing range with range Id [{}]",rangeId);
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("rangeId", rangeId);
        return namedParameterJdbcTemplate.update(closeRange,parameters);
    }

    @Override
    public int addTask(Task task) {
        LOG.info("Adding task for user Id [{}]",task.getUserId());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", task.getUserId());
        parameters.put("taskName", task.getName());
        parameters.put("taskDescription", task.getDescription());
        return namedParameterJdbcTemplate.update(addTask,parameters);
    }

    @Override
    public int closeTask(Long taskId) {
        LOG.info("Closing task with task Id [{}]",taskId);
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("taskId", taskId);
        return namedParameterJdbcTemplate.update(closeTask,parameters);
    }
}
