package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.contact_with_us_helpers.Contact;
import com.Ahmed.SoltanSalman.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Contact", description = "Endpoints for handling complaints")
public class ContactController {
    private final ContactService service;

    @PostMapping("/contact")
    @Operation(summary = "Submit a complaint", description = "Allows users to submit a complaint")
    public String makeComplaint(@RequestBody Contact contact) {
        return service.addComplaint(contact);
    }
}