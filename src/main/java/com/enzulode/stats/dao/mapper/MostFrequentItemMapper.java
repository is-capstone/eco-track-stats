package com.enzulode.stats.dao.mapper;

import com.enzulode.stats.dao.row.MostFrequentItem;
import com.enzulode.stats.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class MostFrequentItemMapper implements Mapper<MostFrequentItem> {

  @Override
  public MostFrequentItem map(ResultSet rs) {
    try {
      var endpoint = rs.getString("endpoint");
      var requestCount = rs.getLong("request_count");
      return new MostFrequentItem(endpoint, requestCount);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }
}
