package com.Ahmed.SoltanSalman.contact_us_Page;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContacPageController {
    private final ContactPageService service;
    @GetMapping("/contactUs")
    public ContactUs getData(){
        return service.getData();
    }
}
