package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.dao.GlobalRepo;
import com.Ahmed.SoltanSalman.global_helpers.Global;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalService {
    private final GlobalRepo repo;
    public Global getData(){
        ObjectId id=new ObjectId("684be0e3317edce7abfc0294");
        return repo.findById(id).orElseThrow();
    }
}
