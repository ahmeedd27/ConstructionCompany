package com.Ahmed.SoltanSalman.global;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalRepo extends MongoRepository<Global , ObjectId> {
}
