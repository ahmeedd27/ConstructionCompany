package com.Ahmed.SoltanSalman.newspage;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsPageService {
    private final NewsPageRepo repo;
    public NewsPage getData(){
        ObjectId id=new ObjectId("6846d6f23d6eff3e75de9302");
        return repo.findById(id).orElseThrow();
    }
}
