package com.Ahmed.SoltanSalman.contact;

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
