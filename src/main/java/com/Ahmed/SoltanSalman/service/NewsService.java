package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.dao.NewsPageRepo;
import com.Ahmed.SoltanSalman.dao.NewsRepo;
import com.Ahmed.SoltanSalman.news_helpers.New;
import com.Ahmed.SoltanSalman.news_page_helpers.*;
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
        repo.save(n);
        NewP np=new NewP(
                n.get_id(),
                n.getSlug(),
                n.getHeader().getTitle(),
                n.getHeader().getDesc(),
                n.getHeader().getImgUrl(),
                n.isFeatured(),
                n.getHeader().getCategory()

        );
        NewsPage newsPage=newsPageService.getData();
        newsPage.getNews().add(np);
        newsPageRepo.save(newsPage);

        return "Saved Successfully";

    }
}
