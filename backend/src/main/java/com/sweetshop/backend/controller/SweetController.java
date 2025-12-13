package com.sweetshop.backend.controller;

import com.sweetshop.backend.model.Sweet;
import com.sweetshop.backend.service.SweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @PostMapping
    public ResponseEntity<Sweet> createSweet(@RequestBody Sweet sweet) {
        Sweet newSweet = sweetService.addSweet(sweet);
        return new ResponseEntity<>(newSweet, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        List<Sweet> sweets = sweetService.getAllSweets();
        return new ResponseEntity<>(sweets, HttpStatus.OK);
    }
}