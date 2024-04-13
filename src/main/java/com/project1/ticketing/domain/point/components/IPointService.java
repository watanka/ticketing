package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequestDTO;
import com.project1.ticketing.api.dto.response.PointResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface IPointService {

    PointResponseDTO updatePoint(PointRequestDTO pointRequestDTO);
    PointResponseDTO checkPoint(long userId);
}
