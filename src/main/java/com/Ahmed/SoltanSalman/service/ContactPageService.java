package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.contact_us_page_helpers.ContactUs;
import com.Ahmed.SoltanSalman.dao.ContactPageRepo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactPageService {
    private final ContactPageRepo repo;
    public ContactUs getData(){
        ObjectId id=new ObjectId("684be0fb317edce7abfc0298");
        return repo.findById(id).orElseThrow();
    }
}
