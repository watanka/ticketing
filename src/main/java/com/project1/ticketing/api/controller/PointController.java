package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.PointRequestDTO;
import com.project1.ticketing.api.dto.response.PointResponseDTO;
import com.project1.ticketing.domain.point.components.IPointService;
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
    public ResponseEntity<PointResponseDTO> updatePoint(@RequestBody PointRequestDTO pointRequestDTO){
        PointResponseDTO pointResponseDTO = pointService.updatePoint(pointRequestDTO);

        return ResponseEntity.ok().body(pointResponseDTO);
    }

    @GetMapping("/points/{user_id}")
    public ResponseEntity<PointResponseDTO> checkPoint(@PathVariable(value="user_id") long userId){
        PointResponseDTO pointResponseDTO = pointService.checkPoint(userId);

        return ResponseEntity.ok().body(pointResponseDTO);
    }

}
