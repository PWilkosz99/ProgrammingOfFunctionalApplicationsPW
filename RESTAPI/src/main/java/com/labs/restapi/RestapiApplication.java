package com.labs.restapi;

import entity.EntityUtil;
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

    @DeleteMapping("/train/{id}")
    void deleteTrain(@PathVariable int id) {
        EntityUtil.deleteTrainByID(id);
    }

    @DeleteMapping("/trainstation/{id}")
    void deleteStation(@PathVariable int id) {
        EntityUtil.deleteStationByID(id);
    }

}
