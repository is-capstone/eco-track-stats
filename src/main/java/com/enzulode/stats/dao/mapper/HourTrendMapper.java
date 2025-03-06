package com.enzulode.stats.dao.mapper;

import com.enzulode.stats.dao.row.HourTrendItem;
import com.enzulode.stats.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class HourTrendMapper implements Mapper<HourTrendItem> {

  @Override
  public HourTrendItem map(ResultSet rs) {
    try {
      var hour = rs.getLong("hour");
      var requests = rs.getLong("requests");
      return new HourTrendItem(hour, requests);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }
}
