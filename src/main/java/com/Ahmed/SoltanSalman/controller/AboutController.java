package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.about_page_helpers.About;
import com.Ahmed.SoltanSalman.service.AboutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "About", description = "Endpoints for about page data")
public class AboutController {
    private final AboutService service;

    @GetMapping("/about-page")
    @Operation(summary = "Retrieve about page data", description = "Fetches data for the about page")
    public About getData() {
        return service.getData();
    }
}