package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.components.IPointHistoryService;
import com.project1.ticketing.domain.point.components.PointHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointController {

    IPointHistoryService pointHistoryService;

    @Autowired
    public PointController(IPointHistoryService pointService) {
        this.pointHistoryService = pointHistoryService;
    }

    @PostMapping("/points")
    public ResponseEntity<PointHistoryResponse> updatePoint(@RequestBody PointRequest pointRequest){
        PointHistoryResponse pointResponse = pointHistoryService.updatePoint(pointRequest);
        return ResponseEntity.ok().body(pointResponse);
    }


    @GetMapping("/points/{user_id}")
    public ResponseEntity<PointHistoryResponse> checkBalance(@PathVariable(value="user_id") long userId){
        PointHistoryResponse pointHistoryResponse = pointHistoryService.checkBalance(userId);
        return ResponseEntity.ok().body(pointHistoryResponse);

    }

    @GetMapping("/point-history/{user_id}")
    public ResponseEntity<List<PointHistoryResponse>> checkAllPointhistory(@PathVariable(value="user_id") long userId){
        List<PointHistoryResponse> pointHistoryResponseList = pointHistoryService.getAllPointHistory(userId);
        return ResponseEntity.ok().body(pointHistoryResponseList);

    }


}
