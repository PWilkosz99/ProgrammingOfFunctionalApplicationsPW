package com.labs.restapi;

import entity.EntityUtil;
import entity.RatingEntity;
import entity.StationsEntity;
import entity.TrainsEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class RestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/api/train")
    void newTrain(String newTrainName) {  //TrainsEntity newTrain
        EntityUtil.addToDB(new TrainsEntity(newTrainName));
    }

    @PostMapping("/api/trainstation")
    void newStation(String newStationName) {  //StationsEntity newStation
        EntityUtil.addToDB(new StationsEntity(newStationName));
    }

    @PostMapping("/api/rating")
    void newStation(int ID, int rate) {
        EntityUtil.addToDB(new RatingEntity(rate,ID));
    }

    @DeleteMapping("/api/train/{id}")
    void deleteTrain(@PathVariable int id) {
        EntityUtil.deleteTrainByID(id);
    }

    @DeleteMapping("/api/trainstation/{id}")
    void deleteStation(@PathVariable int id) {
        EntityUtil.deleteStationByID(id);
    }
}
