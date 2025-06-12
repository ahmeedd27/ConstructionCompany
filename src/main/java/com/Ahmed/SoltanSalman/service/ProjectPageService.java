package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.dao.ProjectPageRepo;
import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectPageService {
    private final ProjectPageRepo repo;

    public ProjectPage getData() {
        ObjectId id=new ObjectId("684ae086ae15ac52663b9af8");
       return repo.findById(id).orElseThrow();
    }
}
