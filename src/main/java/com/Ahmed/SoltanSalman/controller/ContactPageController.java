package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.service.ContactPageService;
import com.Ahmed.SoltanSalman.contact_us_page_helpers.ContactUs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Contact Page", description = "Endpoints for contact page data")
public class ContactPageController {
    private final ContactPageService service;

    @GetMapping("/contact-page")
    @Operation(summary = "Retrieve contact page data", description = "Fetches data for the contact us page")
    public ContactUs getData() {
        return service.getData();
    }
}