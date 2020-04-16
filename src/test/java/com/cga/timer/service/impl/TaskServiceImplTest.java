package com.cga.timer.service.impl;

import com.cga.timer.model.RangeDates;


import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @InjectMocks
    @Spy
    TaskServiceImpl taskService;


    private Set<RangeDates> workingDates = new HashSet<>();

    @Mock
    private RangeDates mockFirstRange = new RangeDates();
    @Mock
    private RangeDates mockSecondRange = new RangeDates();

    @Before
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void calculateTime() throws ParseException {
        workingDates.add(mockFirstRange);
        workingDates.add(mockSecondRange);
        doReturn(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("02/01/2020 10:05:16")).when(mockFirstRange).getStartDate();
        doReturn(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("02/01/2020 11:24:36")).when(mockFirstRange).getEndDate();

        doReturn(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("03/01/2020 09:24:17")).when(mockSecondRange).getStartDate();
        doReturn(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("03/01/2020 11:59:26")).when(mockSecondRange).getEndDate();


        taskService.calculateTime(workingDates);
    }
}