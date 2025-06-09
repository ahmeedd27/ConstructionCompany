package com.Ahmed.SoltanSalman.projectpage;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectPageService {
    private final ProjectPageRepo repo;

    public ProjectPage getData() {
        ObjectId id=new ObjectId("6846af1b39b9e39ca57b9a98");
       return repo.findById(id).orElseThrow();
    }
}
