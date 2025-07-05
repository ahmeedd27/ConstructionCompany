package com.Ahmed.SoltanSalman.news_functionality;


import com.Ahmed.SoltanSalman.comman_helpers.COARequest;
import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.Ahmed.SoltanSalman.home_functionality.Home;
import com.Ahmed.SoltanSalman.project_functionality.ProjectDTO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    private final Random random;


    public String generateSlug(String baseName) {
        if (baseName == null || baseName.trim().isEmpty()) {
            baseName = "project";
        }
        // تحويل إلى kebab-case وإزالة الرموز غير المسموح بها
        String sanitized = baseName.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "") // إزالة الرموز غير المسموح بها
                .replaceAll("\\s+", "-"); // استبدال المسافات بـ -
        // إضافة رقم عشوائي من 100 إلى 999
        int randomNum = random.nextInt(900) + 100;
        return sanitized + "-" + randomNum;
    }


    public New addNew(CreateNewsRequest request) {
        if (request == null) throw new IllegalArgumentException("no valid");
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
        } catch (IOException e) {
            throw new RuntimeException("Image upload problem");
        }

        return temp.save(New.builder()
                .slug(generateSlug(request.getTitle().getEn()))
                .header(new Header(request.getTitle(), request.getDesc(), url))
                .article(request.getArticle())
                .createdAt(new Date())
                .isFeatured(request.isFeatured())
                .build(), "News");

    }


    public New updateNew(String slug, NewRequest request) {
        if (slug == null || slug.isEmpty())
            throw new NoSuchElementException("there is no slug");

        Query q = new Query(Criteria.where("slug").is(slug));
        New existed = temp.findOne(q, New.class);
        if (existed == null)
            throw new NoSuchElementException("News not found");


        if (request.getTitle() != null) {
            if (request.getTitle().getEn() != null)
                existed.getHeader().getTitle().setEn(request.getTitle().getEn());

            if (request.getTitle().getAr() != null)
                existed.getHeader().getTitle().setAr(request.getTitle().getAr());
        }
        if (request.getDesc() != null) {
            if (request.getDesc().getEn() != null)
                existed.getHeader().getDesc().setEn(request.getDesc().getEn());

            if (request.getDesc().getAr() != null)
                existed.getHeader().getDesc().setAr(request.getDesc().getAr());
        }
        if (request.getImageBase64() != null && !request.getImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            try {
                String base64Data = request.getImageBase64().split(",")[1];
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                String url = uploadResult.get("secure_url").toString();
                existed.getHeader().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }

        if (request.getArticle() != null) {
            Article existingArticle = existed.getArticle();
            Article incomingArticle = request.getArticle();
            if (incomingArticle.getEn() != null)
                existingArticle.setEn(incomingArticle.getEn());

            if (incomingArticle.getAr() != null)
                existingArticle.setAr(incomingArticle.getAr());

            existed.setArticle(existingArticle);
        }
        if (request.getIsFeatured() != null)
            existed.setIsFeatured(request.getIsFeatured());

        return temp.save(existed, "News");
    }


    public List<New> getAllNews() {
        List<New> list = temp.findAll(New.class);
        if (list.isEmpty()) {
            throw new NoSuchElementException("No elements");
        }
        return list;
    }

    public New getNewBySlug(String slug) {
        if (slug == null || slug.isEmpty()) throw new IllegalArgumentException("No Slug");
        Query q = new Query();
        q.addCriteria(Criteria.where("slug").is(slug));
        New n = temp.findOne(q, New.class);
        if (n == null) throw new NoSuchElementException("New not found with slug: " + slug);
        return n;
    }

    public String deleteNewBySlug(String slug) {
        if (slug == null || slug.isEmpty()) throw new IllegalArgumentException("No Slug");
        Query q = new Query();
        q.addCriteria(Criteria.where("slug").is(slug));
        DeleteResult result = temp.remove(q, New.class);
        if (result.getDeletedCount() == 0) {
            throw new NoSuchElementException("No news found with the given slug");
        }
        return "Deleted Successfully";
    }


}
