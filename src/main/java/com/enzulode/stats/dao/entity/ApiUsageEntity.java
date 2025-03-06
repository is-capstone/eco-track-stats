package com.enzulode.stats.dao.entity;

import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiUsageEntity(
    UUID id,
    String endpoint,
    RequestMethod method,
    LocalDateTime at,
    String usedBy
){}
