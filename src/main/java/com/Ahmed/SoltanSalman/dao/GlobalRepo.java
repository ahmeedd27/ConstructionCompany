package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.global_helpers.Global;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalRepo extends MongoRepository<Global, ObjectId> {
}
