package com.Ahmed.SoltanSalman.newspage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsPageController {
    private final NewsPageService service;
    @GetMapping("newsPage")
    public NewsPage getData(){
        return service.getData();
    }
}
