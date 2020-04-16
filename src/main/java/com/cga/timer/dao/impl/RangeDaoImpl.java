package com.cga.timer.dao.impl;

import com.cga.timer.dao.RangeDao;
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
public class RangeDaoImpl implements RangeDao {
    private static final Logger LOG = LoggerFactory.getLogger(RangeDaoImpl.class);

    @Value("${sql.get.range.by.user.id}")
    private String getRangeDatesByUserId;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RangeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<RangeDates> getRangeDatesByUserId(Long userId) {
        LOG.info("Getting Ranges for user Id [{}]",userId);
        Map<String, Long> parameters = new HashMap<>();
        parameters.put("userId", userId);

        return namedParameterJdbcTemplate.query(getRangeDatesByUserId,parameters, RangeDates.rowMapper());
    }
}
