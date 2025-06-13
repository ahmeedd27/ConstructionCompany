package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.dao.HomeRepo;
import com.Ahmed.SoltanSalman.home_helpers.Home;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepo repo;
    public Home getHomePage(){
        ObjectId id=new ObjectId("684be05b317edce7abfc0276");
        return repo.findById(id).orElseThrow();
    }
}
