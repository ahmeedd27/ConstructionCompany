package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.about_page_helpers.About;
import com.Ahmed.SoltanSalman.dao.AboutRepo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService {
    private final AboutRepo repo;
    public About getData(){
        ObjectId id=new ObjectId("684be077317edce7abfc0279");
        return repo.findById(id).orElseThrow();
    }
}
