package com.enzulode.stats.dto.api;

import com.enzulode.stats.dao.row.HourTrendItem;

import java.util.List;

public record HourTrendDto(List<HourTrendItem> data, int count) {}
