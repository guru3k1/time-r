package com.cga.timer.dao;

import com.cga.timer.model.RangeDates;

import java.util.List;

public interface RangeDao {

    List<RangeDates> getRangeDatesByUserId(Long userId);
}
