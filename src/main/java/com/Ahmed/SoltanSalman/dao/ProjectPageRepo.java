package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPageRepo extends MongoRepository<ProjectPage, ObjectId> {
}
