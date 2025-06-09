package com.Ahmed.SoltanSalman.projects;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepo extends MongoRepository<Project , String> {
    Project findBySlug(String slug);
}
