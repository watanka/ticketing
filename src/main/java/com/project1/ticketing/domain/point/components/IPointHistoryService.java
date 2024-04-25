package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;

import java.util.List;

public interface IPointHistoryService {
    PointHistoryResponse updatePoint(PointRequest request);
    List<PointHistoryResponse> getAllPointHistory(long userId);
    PointHistoryResponse checkBalance(long userId);
}
