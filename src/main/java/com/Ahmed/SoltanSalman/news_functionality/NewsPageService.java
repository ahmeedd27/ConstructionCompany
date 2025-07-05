package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.PageUpdateRequest;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewsPageService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    private final ObjectMapper mapper;


    public NewsPageDTO getNewsPage() {
        List<New> newsList = temp.find(new Query(), New.class, "News");
        List<NewDto> newsDTOs = newsList.stream().map(n -> NewDto.builder()
                ._id(n.get_id())
                .slug(n.getSlug())
                .header(n.getHeader())
                .createdAt(n.getCreatedAt())
                .isFeatured(n.getIsFeatured())
                .build()).toList();
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page == null) throw new NoSuchElementException("No Page");
        return NewsPageDTO.builder()
                .header(page.getHeader())
                .NewsDtoList(newsDTOs)
                .build();
    }

    public NewsPage updateNewsPageHeader(PageUpdateRequest request) {
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page == null) throw new NoSuchElementException("No Page");
        if (request.getTitle() != null) {
            if (request.getTitle().getEn() != null)
                page.getHeader().getTitle().setEn(request.getTitle().getEn());

            if (request.getTitle().getAr() != null)
                page.getHeader().getTitle().setAr(request.getTitle().getAr());
        }
        if (request.getDesc() != null) {
            if (request.getDesc().getEn() != null)
                page.getHeader().getDesc().setEn(request.getDesc().getEn());

            if (request.getDesc().getAr() != null)
                page.getHeader().getDesc().setAr(request.getDesc().getAr());
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
        return temp.save(page, "NewsPage");
    }

}
