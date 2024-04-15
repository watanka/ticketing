package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointResponse;
import org.springframework.stereotype.Service;

@Service
public class PointService implements IPointService{
    @Override
    public PointResponse updatePoint(PointRequest pointRequest) {
        return null;
    }

    @Override
    public PointResponse checkPoint(long userId) {
        return null;
    }
}
