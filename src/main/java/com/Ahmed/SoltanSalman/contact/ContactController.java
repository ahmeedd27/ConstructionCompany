package com.Ahmed.SoltanSalman.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {
     private final ContactService service;
     @PostMapping("/complaint")
    public String makeComplaint(
            @RequestBody Contact contact
     ){
         return  service.addComplaint(contact);
     }
}
