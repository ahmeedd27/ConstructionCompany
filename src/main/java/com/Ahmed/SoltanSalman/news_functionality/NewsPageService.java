package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewsPageService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    private final ObjectMapper mapper;


    public NewsPage getNewsPage() {
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page == null) throw new NoSuchElementException("No Page");
        return page;
    }

    public NewsPage updateNewsPageHeader(NewsPageUpdateRequest request) {
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page == null) throw new NoSuchElementException("No Page");

        if (request.getHeader() != null) {
            if (request.getHeader().getTitle() != null) {
                if (request.getHeader().getTitle().getEn() != null)
                    page.getHeader().getTitle().setEn(request.getHeader().getTitle().getEn());

                if (request.getHeader().getTitle().getAr() != null)
                    page.getHeader().getTitle().setAr(request.getHeader().getTitle().getAr());
            }
            if (request.getHeader().getDesc() != null) {
                if (request.getHeader().getDesc().getEn() != null)
                    page.getHeader().getDesc().setEn(request.getHeader().getDesc().getEn());

                if (request.getHeader().getDesc().getAr() != null)
                    page.getHeader().getDesc().setAr(request.getHeader().getDesc().getAr());
            }
        }
        if (request.getImageBase64() != null && !request.getImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            String url = "";
            String base64Data = request.getImageBase64().split(",")[1];
            try {
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                url = uploadResult.get("secure_url").toString();
                page.getHeader().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        return temp.save(page , "NewsPage");
    }

}
