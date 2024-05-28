package com.project1.ticketing.api.dto.request;


import com.project1.ticketing.domain.point.models.PointType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public record PointRequest(long userId,
                           long amount,
                           PointType pointType){}
