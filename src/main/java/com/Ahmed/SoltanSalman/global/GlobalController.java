package com.Ahmed.SoltanSalman.global;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GlobalController {
    private final GlobalService service;
    @GetMapping("/global")
    public Global getData(){
       return service.getData();
    }
}
