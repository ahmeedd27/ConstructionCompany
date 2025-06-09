package com.Ahmed.SoltanSalman.newspage;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPageRepo extends MongoRepository<NewsPage , ObjectId> {
}
