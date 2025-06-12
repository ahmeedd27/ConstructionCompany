package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.projects_helpers.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepo extends MongoRepository<Project, String> {
    Project findBySlug(String slug);
}
