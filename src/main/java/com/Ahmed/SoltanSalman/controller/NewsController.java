package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.news_helpers.New;
import com.Ahmed.SoltanSalman.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
@Tag(name = "News", description = "Endpoints for managing news items")
public class NewsController {
    private final NewsService service;

    @GetMapping()
    @Operation(summary = "Retrieve all news items", description = "Fetches a list of all news items")
    public List<New> findAll() {
        return service.findAll();
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Retrieve news item by slug", description = "Fetches a specific news item by its slug")
    public New getNewBySlug(@PathVariable String slug) {
        return service.getNewBySlug(slug);
    }

    @PostMapping()
    @Operation(summary = "Create a new news item", description = "Adds a new news item with the provided details")
    public String createNew(@RequestBody New n) {
        return service.addNew(n);
    }
}