package com.vishesh9310.bookstoreApi.Public;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class Healthcheck {

    @GetMapping
    public ResponseEntity<?> getHealthcheck(){
        return new ResponseEntity<>("server is run and health is good",HttpStatus.OK);
    }
}
