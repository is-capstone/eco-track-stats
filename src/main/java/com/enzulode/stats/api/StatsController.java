package com.enzulode.stats.api;

import com.enzulode.stats.dto.api.HourTrendDto;
import com.enzulode.stats.dto.api.MostFrequentDto;
import com.enzulode.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

  private final StatsService statsService;

  @GetMapping("/frequent")
  public MostFrequentDto frequent(@RequestParam("top") int top) {
    return statsService.mostFrequent(top);
  }

  @GetMapping("/hour")
  public HourTrendDto hourTrend() {
    return statsService.hourTrend();
  }

  @GetMapping("/suspicious/min")
  public Object suspiciousRequestsMinutes(@RequestParam("interval") long interval, @RequestParam(name = "multiplier", required = false) Long multiplier) {
    return statsService.suspiciousUserActivityMinutes(interval, multiplier);
  }
}
