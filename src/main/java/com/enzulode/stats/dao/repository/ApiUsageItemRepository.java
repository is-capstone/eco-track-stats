package com.enzulode.stats.dao.repository;

import com.enzulode.stats.dao.entity.ApiUsageEntity;
import com.enzulode.stats.dao.row.HourTrendItem;
import com.enzulode.stats.dao.row.MostFrequentItem;
import com.enzulode.stats.dao.row.SuspiciousUserActivityItem;

import java.util.List;

public interface ApiUsageItemRepository {

  int create(ApiUsageEntity apiUsageEntity);


  List<MostFrequentItem> mostFrequent(long topOf);

  List<HourTrendItem> hourTrend();

  List<SuspiciousUserActivityItem> suspiciousUserActivityInMinutesInterval(long interval, long multiplier);
}
