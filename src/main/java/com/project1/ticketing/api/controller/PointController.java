package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointResponse;
import com.project1.ticketing.domain.point.components.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PointController {

    PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/points")
    public ResponseEntity<PointResponse> updatePoint(@RequestBody PointRequest pointRequest){
//        PointResponse pointResponse = pointService.updatePoint(pointRequest);
//
//        return ResponseEntity.ok().body(pointResponse);
        return null;
    }

    @GetMapping("/points/{user_id}")
    public ResponseEntity<PointResponse> checkPoint(@PathVariable(value="user_id") long userId){
//        PointResponse pointResponse = pointService.checkPoint(userId);
//
//        return ResponseEntity.ok().body(pointResponse);
        return null;
    }

}
