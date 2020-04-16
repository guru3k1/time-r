package com.cga.timer.service.impl;

import com.cga.timer.dao.RangeDao;
import com.cga.timer.dao.TaskDao;
import com.cga.timer.model.RangeDates;
import com.cga.timer.model.Task;
import com.cga.timer.service.TaskService;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
                t.setWorkingDates(ranges.stream().filter(r -> r.getTask_id() == t.getTaskId()).collect(Collectors.toSet()));
                if(!t.getWorkingDates().isEmpty()){
                    t.setElapsedTime(calculateTime(t.getWorkingDates()));
                }
            });
        }catch (Exception e){
            LOG.error("Error on [{}]",e.getMessage());
        }
        return tasks;
    }

    protected String calculateTime(Set<RangeDates> workingDates) {
        LOG.info("-> Calculate Time");
        Duration sumDuration = Duration.ZERO;

        for(RangeDates range :workingDates ){
            DateTime startDate = new DateTime(range.getStartDate());
            DateTime endDate = new DateTime(range.getEndDate());
            Duration duration = new Duration(startDate,endDate);
            sumDuration= sumDuration.plus(duration);
        }
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
}
