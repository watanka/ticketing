package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequestDTO;
import com.project1.ticketing.api.dto.response.PointResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PointService implements IPointService{
    @Override
    public PointResponseDTO updatePoint(PointRequestDTO pointRequestDTO) {
        return null;
    }

    @Override
    public PointResponseDTO checkPoint(long userId) {
        return null;
    }
}
