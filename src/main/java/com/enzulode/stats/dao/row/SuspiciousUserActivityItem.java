package com.enzulode.stats.dao.row;

public record SuspiciousUserActivityItem(String usedBy, Long requestCount, Long avgRequests) {}
