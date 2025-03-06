package com.enzulode.stats.dao.mapper;

import com.enzulode.stats.dao.row.SuspiciousUserActivityItem;
import com.enzulode.stats.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class SuspiciousUserActivityItemMapper implements Mapper<SuspiciousUserActivityItem> {

  @Override
  public SuspiciousUserActivityItem map(ResultSet rs) {
    try {
      var usedBy = rs.getString("used_by");
      var requestCount = rs.getLong("request_count");
      var avgRequests = rs.getLong("avg_requests");
      return new SuspiciousUserActivityItem(usedBy, requestCount, avgRequests);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }
}
