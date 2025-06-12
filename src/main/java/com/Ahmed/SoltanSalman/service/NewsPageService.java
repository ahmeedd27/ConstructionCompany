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
        ObjectId id=new ObjectId("6849e3221327da7d7a04c598");
        return repo.findById(id).orElseThrow();
    }
}
