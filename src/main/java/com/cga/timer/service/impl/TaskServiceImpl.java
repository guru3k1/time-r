package com.cga.timer.service.impl;

import com.cga.timer.dao.RangeDao;
import com.cga.timer.dao.TaskDao;
import com.cga.timer.model.RangeDates;
import com.cga.timer.model.Task;
import com.cga.timer.service.TaskService;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);
    protected TaskDao taskDao;
    protected RangeDao rangeDao;

    public TaskServiceImpl(TaskDao taskDao, RangeDao rangeDao) {
        this.taskDao = taskDao;
        this.rangeDao = rangeDao;
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        LOG.info("Getting tasks by user id [{}]",userId);
        List<Task> tasks = taskDao.getTaksByUserId(userId);
        List<RangeDates> ranges = rangeDao.getRangeDatesByUserId(userId);
        try{
            tasks.forEach(t -> {
                t.setWorkingDates(ranges.stream().filter(r -> r.getTaskId() == t.getTaskId()).collect(Collectors.toSet()));
                if(!t.getWorkingDates().isEmpty()){
                    t.setElapsedTime(calculateTime(t.getWorkingDates(),t.getTaskId()));
                }
            });
        }catch (Exception e){
            LOG.error("Error on [{}]",e.getMessage());
        }
        return tasks;
    }

    protected String calculateTime(Set<RangeDates> workingDates,Long taskId) {
        LOG.info("-> Calculate Time");
        Duration sumDuration = Duration.ZERO;

        for(RangeDates range :workingDates ){
            if(range.getEndDate()!=null){
                Duration duration = new Duration(range.getStartDate().getTime(),range.getEndDate().getTime());
                sumDuration= sumDuration.plus(duration);
            }
        }
        LOG.info("Time for range {}: [{}]",taskId,sumDuration);
        LOG.info("<- Calculate Time");
        return sumDuration.toPeriod().toString().substring(2);
    }

    @Override
    public String deleteTask(Long taskId) {
        LOG.info("Delete task with id [{}]",taskId);
        int status = taskDao.deleteTask(taskId);
        if(status == 1){
            LOG.info("Task with id [{}] successfully deleted", taskId);
            return "Task successfully deleted";
        }else {
            LOG.info("Error deleting task with id [{}]", taskId);
            return "Error deleting task with id: "+taskId;
        }
    }

    @Override
    public Task saveTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public String addRange(RangeDates rangeDates) {
        LOG.info("Add range to taskId [{}]",rangeDates.getTaskId());
        int status = taskDao.addRange(rangeDates);
        if(status == 1){
            LOG.info("Range with task id [{}] successfully added", rangeDates.getTaskId());
            return "Range successfully added";
        }else {
            LOG.info("Error adding range with taskid [{}]", rangeDates.getTaskId());
            return "Error adding range with taskid: "+rangeDates.getTaskId();
        }
    }

    @Override
    public String closeRange(Long rangeId) {
        LOG.info("Close range to rangeId [{}]",rangeId);
        int status = taskDao.closeRange(rangeId);
        if(status == 1){
            LOG.info("Range with range id [{}] successfully closed", rangeId);
            return "Range successfully closed";
        }else {
            LOG.info("Error closing range with rangeid [{}]", rangeId);
            return "Error closing range with rangeid: "+rangeId;
        }
    }

    @Override
    public String addTask(Task task) {
        LOG.info("Add task for userId [{}]",task.getUserId());
        int status = taskDao.addTask(task);
        if(status == 1){
            LOG.info("Task for user id [{}] successfully closed", task.getUserId());
            return "Task successfully added";
        }else {
            LOG.info("Error adding task with userid [{}]", task.getUserId());
            return "Error adding task with userid: "+task.getUserId();
        }
    }

    @Override
    public String closeTask(Long taskId) {
        LOG.info("Close task with taskId [{}]",taskId);
        int status = taskDao.closeTask(taskId);
        if(status == 1){
            LOG.info("Task with task id [{}] successfully closed", taskId);
            return "Task successfully closed";
        }else {
            LOG.info("Error closing task with taskId [{}]", taskId);
            return "Error closing task with taskId: "+taskId;
        }
    }
}
