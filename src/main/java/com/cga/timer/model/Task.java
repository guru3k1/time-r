package com.cga.timer.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

public class Task {
    private Long userId;
    private Long taskId;
    private String name;
    private String description;
    private Date startedDate;
    private Date closedDate;
    private Set<RangeDates> workingDates;
    private String elapsedTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Set<RangeDates> getWorkingDates() {
        return workingDates;
    }

    public void setWorkingDates(Set<RangeDates> workingDates) {
        this.workingDates = workingDates;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public static RowMapper<Task> rowMapper(){
        return (rs,rowNum)->{
        Task task = new Task();
        task.setUserId(rs.getLong("USER_ID"));
        task.setTaskId(rs.getLong("TASK_ID"));
        task.setName(rs.getString("TASK_NAME"));
        task.setDescription(rs.getString("TASK_DESCRIPTION"));
        task.setStartedDate(rs.getDate("START_DATE"));
        task.setClosedDate(rs.getDate("CLOSE_DATE"));
        return task;
        };
    }
}
