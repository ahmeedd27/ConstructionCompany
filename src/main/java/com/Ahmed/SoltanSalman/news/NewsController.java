package com.Ahmed.SoltanSalman.news;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsService service;
    @GetMapping("/news")
    public List<New> findAll(){
        return service.findAll();
    }
    @GetMapping("/news/{slug}")
    public New getNewBySlug(
            @PathVariable String slug
    ){
        return service.getNewBySlug(slug);
    }
    @PostMapping("/news/add")
    public String createNew(
            @RequestBody New n
    ){
        return service.addNew(n);
    }
}
