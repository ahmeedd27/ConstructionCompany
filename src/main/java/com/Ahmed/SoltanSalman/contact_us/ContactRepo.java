package com.Ahmed.SoltanSalman.contact_us;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends MongoRepository<ContactUs , ObjectId> {
}
