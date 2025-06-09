package com.Ahmed.SoltanSalman.news;

import com.Ahmed.SoltanSalman.newspage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepo repo;
    private final NewsPageService newsPageService;
    private final NewsPageRepo newsPageRepo;
    public List<New> findAll(){
        return repo.findAll();
    }

    public New getNewBySlug(String slug) {
        return repo.findBySlug(slug);
    }

    public String addNew(New n){
        NewP np=new NewP(
                n.getHeader().getTitle(),
                n.getHeader().getDesc(),
                n.getHeader().getImgUrl(),
                n.isFeatured()
        );
        NewsPage newsPage=newsPageService.getData();
        newsPage.getNews().add(np);
        newsPageRepo.save(newsPage);
        repo.save(n);
        return "Saved Successfully";

    }
}
