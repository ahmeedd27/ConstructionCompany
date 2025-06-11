package com.Ahmed.SoltanSalman.contact_us_Page;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactPageService {
    private final ContactPageRepo repo;
    public ContactUs getData(){
        ObjectId id=new ObjectId("68443c368a04d239c9a7bb30");
        return repo.findById(id).orElseThrow();
    }
}
