package com.cga.timer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class RangeDates {
    private Long userId;
    private Long rangeId;
    private Long task_id;
    private Date startDate;
    private Date endDate;

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRangeId() {
        return rangeId;
    }

    public void setRangeId(Long rangeId) {
        this.rangeId = rangeId;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static RowMapper<RangeDates> rowMapper(){
        return (rs,rowNum)->{
            RangeDates rangeDates = new RangeDates();
            rangeDates.setTask_id(rs.getLong("TASK_ID"));
            rangeDates.setRangeId(rs.getLong("RANGE_ID"));
            rangeDates.setStartDate(rs.getDate("START_DATE"));
            rangeDates.setEndDate(rs.getDate("CLOSE_DATE"));
            return rangeDates;
        };
    }
}
