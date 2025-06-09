package com.Ahmed.SoltanSalman.contact_us;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;
    @GetMapping("/contactUs")
    public ContactUs getData(){
        return service.getData();
    }
}
