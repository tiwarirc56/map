package com.demo.map.controller;

import com.demo.map.service.MapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/smallestPath/{pointA}/{pointB}")
    public ResponseEntity<Object> getSmallestPath(@PathVariable("pointA") String pointA,
                                                  @PathVariable("pointB") String pointB) {
        String output = "";
        if (!pointA.isEmpty() && !pointB.isEmpty()) {
            output = mapService.getSmallestPath(pointA, pointB);
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
    }
}
