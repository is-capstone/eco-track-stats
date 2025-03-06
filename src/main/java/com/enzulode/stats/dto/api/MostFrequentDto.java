package com.enzulode.stats.dto.api;

import com.enzulode.stats.dao.row.MostFrequentItem;

import java.util.List;

public record MostFrequentDto(List<MostFrequentItem> data, int count) {}
