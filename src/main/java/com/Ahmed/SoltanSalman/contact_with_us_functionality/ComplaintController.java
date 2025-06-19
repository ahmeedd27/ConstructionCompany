package com.Ahmed.SoltanSalman.contact_with_us_functionality;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/complaint")
@Tag(name = "Make a Complaint")
public class ComplaintController {
    private final ComplaintService service;

    @PostMapping
    @Operation(summary = "Make a Complaint" , description = "if you have any problem please contact with us!")
    public ResponseEntity<Complaint> addComplaint(
            @RequestBody ComplaintRequest request
    ) {
        return ResponseEntity.ok(service.addComplaint(request));
    }
}
