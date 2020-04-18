package com.cga.timer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class RangeDates {
    private Long userId;
    private Long rangeId;
    private Long taskId;
    private Date startDate;
    private Date endDate;

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRangeId() {
        return rangeId;
    }
    @JsonProperty("rangeId")
    public void setRangeId(Long rangeId) {
        this.rangeId = rangeId;
    }

    public Long getTaskId() {
        return taskId;
    }

    @JsonProperty("taskId")
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static RowMapper<RangeDates> rowMapper(){
        return (rs,rowNum)->{
            RangeDates rangeDates = new RangeDates();
            rangeDates.setTaskId(rs.getLong("TASK_ID"));
            rangeDates.setRangeId(rs.getLong("RANGE_ID"));
            rangeDates.setStartDate(rs.getTimestamp("START_DATE"));
            rangeDates.setEndDate(rs.getTimestamp("CLOSE_DATE"));
            return rangeDates;
        };
    }
}
