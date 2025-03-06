package com.enzulode.stats.listener;

import com.enzulode.stats.dao.entity.ApiUsageEntity;
import com.enzulode.stats.dao.repository.ApiUsageItemRepository;
import com.enzulode.stats.dto.analytics.ApiUsageStatisticsEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiUsageStatsEventListener {

  private final ApiUsageItemRepository repo;

  @RabbitListener(queues = "${api.stats.api-usage-stats-queue}")
  public void handleApiUsageEvent(ApiUsageStatisticsEvent event) {
    var data = new ApiUsageEntity(null, event.endpoint(), event.method(), event.at(), event.usedBy());
    repo.create(data);
  }
}
