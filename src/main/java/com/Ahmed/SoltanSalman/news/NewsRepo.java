package com.Ahmed.SoltanSalman.news;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends MongoRepository<New , String> {
    New findBySlug(String slug);
}
