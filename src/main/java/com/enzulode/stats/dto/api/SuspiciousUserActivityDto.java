package com.enzulode.stats.dto.api;

import com.enzulode.stats.dao.row.SuspiciousUserActivityItem;

import java.util.List;

public record SuspiciousUserActivityDto(List<SuspiciousUserActivityItem> data, int count) {}
