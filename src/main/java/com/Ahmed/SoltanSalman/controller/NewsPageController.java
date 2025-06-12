package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.news_page_helpers.NewsPage;
import com.Ahmed.SoltanSalman.service.NewsPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "News Page", description = "Endpoints for news page data")
public class NewsPageController {
    private final NewsPageService service;

    @GetMapping("/news-page")
    @Operation(summary = "Retrieve news page data", description = "Fetches data for the news page")
    public NewsPage getData() {
        return service.getData();
    }
}