package com.Ahmed.SoltanSalman.contact_us_page_functionality;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact-page")
@Tag(name = "Contact-Page Management")
public class ContactUsController {
    private final ContactUsService service;

    @GetMapping
    @Operation(summary = "Get Contact Us Page Data", description = "Get Contact Us Page")
    public ResponseEntity<Contact> getGlobalData(){
        return ResponseEntity.ok(service.getContactUsPage());
    }
    @PutMapping
    @Operation(summary = "Update Contact Us Page Data",description = "update specific data in Contact Us Page")
    public ResponseEntity<Contact> updateGlobalData(
            @RequestBody ContactUsUpdateRequest request
    ){
        return ResponseEntity.ok(service.updateContactUsPage(request));
    }

}
