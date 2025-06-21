package com.Ahmed.SoltanSalman.home_functionality;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService service;
    @GetMapping
    public ResponseEntity<Home> home(){
        return ResponseEntity.ok(service.getHome());
    }
}
