package com.enzulode.stats.service;

import com.enzulode.stats.dto.api.HourTrendDto;
import com.enzulode.stats.dto.api.MostFrequentDto;
import com.enzulode.stats.dto.api.SuspiciousUserActivityDto;

public interface StatsService {

  MostFrequentDto mostFrequent(int topOf);

  HourTrendDto hourTrend();

  SuspiciousUserActivityDto suspiciousUserActivityMinutes(long interval, Long multiplier);
}
