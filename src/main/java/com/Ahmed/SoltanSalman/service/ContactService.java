package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.contact_with_us_helpers.Contact;
import com.Ahmed.SoltanSalman.dao.ContactRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepo repo;
    public String addComplaint(Contact c){
        repo.save(c);
        return "Saved Successfully";
    }
}
