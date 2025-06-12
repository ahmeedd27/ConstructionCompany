package com.Ahmed.SoltanSalman.dao;

import com.Ahmed.SoltanSalman.news_page_helpers.NewsPage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPageRepo extends MongoRepository<NewsPage, ObjectId> {
}
