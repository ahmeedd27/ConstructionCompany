package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.home_helpers.Home;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepo extends MongoRepository<Home, ObjectId> {
}
