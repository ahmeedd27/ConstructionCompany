package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.contact_us_page_helpers.ContactUs;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPageRepo extends MongoRepository<ContactUs, ObjectId> {
}
