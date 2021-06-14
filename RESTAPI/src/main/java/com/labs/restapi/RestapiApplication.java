package com.labs.restapi;

import entity.EntityUtil;
import entity.RatingEntity;
import entity.StationsEntity;
import entity.TrainsEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    @GetMapping("/api/train/{id}/rating")
    public Double getMean(@PathVariable int id) {
        return EntityUtil.getRateMean(id);
    }

    @RequestMapping(path = "/api/train/csv", method = RequestMethod.GET)
    public ResponseEntity<Resource> getCSV() throws IOException {
        EntityUtil.saveFromDBToCSV();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        File file = new File("Z:\\ProgrammingOfFunctionalApplicationsPW\\RESTAPI\\trains2.csv");
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/api/trainstation")
    public List<StationsEntity> getStations() {
        return EntityUtil.loadStationsDB();
    }

    @GetMapping("/api/trainstation/{id}/train")
    public List<TrainsEntity> getTrainsOnStation(@PathVariable int id) {
        return EntityUtil.getTrainsOnStation(id);
    }

    @GetMapping("/api/train/{id}/fill")
    public Integer getCapacity(@PathVariable int id) {
        return EntityUtil.getTrainByID(id).getCapacity();
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
