package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.home_helpers.Home;
import com.Ahmed.SoltanSalman.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Home", description = "Endpoints for home page functionality")
public class HomeController {
    private final HomeService service;

    @GetMapping("/home-page")
    @Operation(summary = "Retrieve home page data", description = "Fetches the data for the home page")
    public Home getHomePage() {
        return service.getHomePage();
    }
}