package com.enzulode.stats.service;

import com.enzulode.stats.dao.repository.ApiUsageItemRepository;
import com.enzulode.stats.dto.api.HourTrendDto;
import com.enzulode.stats.dto.api.MostFrequentDto;
import com.enzulode.stats.dto.api.SuspiciousUserActivityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StatsServiceImpl implements StatsService {

  private final ApiUsageItemRepository repo;

  @Override
  public MostFrequentDto mostFrequent(int topOf) {
    var queryResult = repo.mostFrequent(topOf);
    return new MostFrequentDto(queryResult, queryResult.size());
  }

  @Override
  public HourTrendDto hourTrend() {
    var queryResult = repo.hourTrend();
    return new HourTrendDto(queryResult, queryResult.size());
  }

  @Override
  public SuspiciousUserActivityDto suspiciousUserActivityMinutes(long interval, Long multiplier) {
    multiplier = multiplier != null ? multiplier : 3;
    var queryResult = repo.suspiciousUserActivityInMinutesInterval(interval, multiplier);
    return new SuspiciousUserActivityDto(queryResult, queryResult.size());
  }
}
