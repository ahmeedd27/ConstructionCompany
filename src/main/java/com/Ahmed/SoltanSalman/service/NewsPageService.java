package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.dao.NewsPageRepo;
import com.Ahmed.SoltanSalman.news_page_helpers.NewsPage;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsPageService {
    private final NewsPageRepo repo;
    public NewsPage getData(){
        ObjectId id=new ObjectId("684be0a4317edce7abfc0285");
        return repo.findById(id).orElseThrow();
    }
}
