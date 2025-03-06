package com.enzulode.stats.dao.repository;

import com.enzulode.stats.dao.entity.ApiUsageEntity;
import com.enzulode.stats.dao.row.HourTrendItem;
import com.enzulode.stats.dao.row.MostFrequentItem;
import com.enzulode.stats.dao.row.SuspiciousUserActivityItem;
import com.enzulode.stats.util.JdbcStream;
import com.enzulode.stats.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiUsageItemRepositoryImpl implements ApiUsageItemRepository {

  private final DataSource ds;
  private final Mapper<MostFrequentItem> mostFrequentItemMapper;
  private final Mapper<HourTrendItem> hourTrendItemMapper;
  private final Mapper<SuspiciousUserActivityItem> suspiciousUserActivityItemMapper;

  @Override
  public int create(ApiUsageEntity item) {
    log.debug("Inserting new API usage item");
    try (var conn = ds.getConnection()) {
      var query = "INSERT INTO api_usage_item (endpoint, method, at, usedBy) VALUES (?, ?, ?, ?)";
      var stmt = conn.prepareStatement(query);
      stmt.setString(1, item.endpoint());
      stmt.setString(2, item.method().name());
      stmt.setObject(3, item.at());
      stmt.setString(4, item.usedBy());
      var res = stmt.executeUpdate();
      log.debug("Insertion {}", res > 0 ? "succeed" : "failed");
      return res;
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return -1;
    }
  }

  @Override
  public List<MostFrequentItem> mostFrequent(long topOf) {
    log.debug("Most frequent API usage items");
    try(var conn = ds.getConnection()) {
      var query = "SELECT endpoint, COUNT(*) as request_count FROM api_usage_item GROUP BY endpoint ORDER BY request_count DESC LIMIT ?";
      var stmt = conn.prepareStatement(query);
      stmt.setLong(1, topOf);
      var res = stmt.executeQuery();
      return JdbcStream.stream(res, mostFrequentItemMapper)
          .filter(Objects::nonNull)
          .toList();
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return List.of();
    }
  }

  @Override
  public List<HourTrendItem> hourTrend() {
    log.debug("Hour trend of API usage items");
    try(var conn = ds.getConnection()) {
      var query = "SELECT HOUR(at) as hour, COUNT(*) as requests FROM api_usage_item GROUP BY hour ORDER BY hour";
      var stmt = conn.prepareStatement(query);
      var res = stmt.executeQuery();
      return JdbcStream.stream(res, hourTrendItemMapper)
          .filter(Objects::nonNull)
          .toList();
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return List.of();
    }
  }

  @Override
  public List<SuspiciousUserActivityItem> suspiciousUserActivityInMinutesInterval(long interval, long multiplier) {
    log.debug("Suspicious user activity (minutes interval)");
    try(var conn = ds.getConnection()) {
      var query = "WITH user_activity AS (SELECT usedBy, COUNT(*) AS request_count, avg(COUNT(*)) OVER () AS avg_requests FROM api_usage_item WHERE at >= now64() - INTERVAL ? minute GROUP BY usedBy) SELECT usedBy as used_by, request_count, avg_requests FROM user_activity WHERE request_count > ? * avg_requests";
      var stmt = conn.prepareStatement(query);
      stmt.setLong(1, interval);
      stmt.setLong(2, multiplier);
      var res = stmt.executeQuery();
      return JdbcStream.stream(res, suspiciousUserActivityItemMapper)
          .filter(Objects::nonNull)
          .toList();
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      return List.of();
    }
  }
}
