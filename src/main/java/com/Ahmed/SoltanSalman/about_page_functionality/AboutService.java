package com.Ahmed.SoltanSalman.about_page_functionality;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AboutService {
    private final MongoTemplate temp;
    public  About getAboutPage(){
        About a=temp.findOne(new Query() , About.class);
        if(a==null) throw new NoSuchElementException();
        return a;
    }

}
