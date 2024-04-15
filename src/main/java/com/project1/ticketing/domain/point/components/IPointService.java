package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointResponse;
import org.springframework.stereotype.Service;

@Service
public interface IPointService {

    PointResponse updatePoint(PointRequest pointRequest);
    PointResponse checkPoint(long userId);
}
