package com.Ahmed.SoltanSalman.global;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalService {
    private final GlobalRepo repo;
    public Global getData(){
        ObjectId id=new ObjectId("6844397b8a04d239c9a7bb2e");
        return repo.findById(id).orElseThrow();
    }
}
