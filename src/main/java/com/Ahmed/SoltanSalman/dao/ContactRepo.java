package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.contact_with_us_helpers.Contact;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends MongoRepository<Contact, ObjectId> {
}
