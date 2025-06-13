package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.about_page_helpers.About;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepo extends MongoRepository<About, ObjectId> {
}
