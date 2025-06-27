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

        New savedNew = temp.save(New.builder()
                .slug(generateSlug(request.getTitle().getEn()))
                .header(new Header(request.getTitle(), request.getDesc(), url))
                .article(request.getArticle())
                .createdAt(new Date())
                .isFeatured(request.isFeatured())
                .build(), "News");
        NewDto dto = NewDto.builder()
                ._id(savedNew.get_id())
                .slug(savedNew.getSlug())
                .header(savedNew.getHeader())
                .isFeatured(savedNew.getIsFeatured())
                .createdAt(savedNew.getCreatedAt())
                .build();

        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page != null) {
            page.getNewsDtoList().add(dto);
            temp.save(page, "NewsPage");
        }
        Home h = temp.findOne(new Query(), Home.class);
        if (h != null) {
            h.getNewsDtoList().addFirst(dto);
            h.getNewsDtoList().removeLast();
            temp.save(h, "Home");
        }
        return savedNew;
    }


    public New updateNew(String slug, NewRequest request) {
        if (slug == null || slug.isEmpty())
            throw new NoSuchElementException("there is no slug");

        Query q = new Query(Criteria.where("slug").is(slug));
        New existed = temp.findOne(q, New.class);
        if (existed == null)
            throw new NoSuchElementException("News not found");

        if (request.getHeader() != null) {
            if (request.getHeader().getTitle() != null) {
                if (request.getHeader().getTitle().getEn() != null)
                    existed.getHeader().getTitle().setEn(request.getHeader().getTitle().getEn());

                if (request.getHeader().getTitle().getAr() != null)
                    existed.getHeader().getTitle().setAr(request.getHeader().getTitle().getAr());
            }
            if (request.getHeader().getDesc() != null) {
                if (request.getHeader().getDesc().getEn() != null)
                    existed.getHeader().getDesc().setEn(request.getHeader().getDesc().getEn());

                if (request.getHeader().getDesc().getAr() != null)
                    existed.getHeader().getDesc().setAr(request.getHeader().getDesc().getAr());
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
        New saved = temp.save(existed, "News");

        //update NewsPage
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page != null && !page.getNewsDtoList().isEmpty()) {
            List<NewDto> list = page.getNewsDtoList();
            for (int i = 0; i < list.size(); i++) {
                if (slug.equals(list.get(i).getSlug())) {
                    NewDto dto = NewDto.builder()
                            .createdAt(saved.getCreatedAt())
                            .isFeatured(saved.getIsFeatured())
                            .header(saved.getHeader())
                            .slug(saved.getSlug())
                            .build();
                    list.set(i, dto);
                    break;
                }
            }
            page.setNewsDtoList(list);
            temp.save(page, "NewsPage");
        }

        //update Home Page
        Home h = temp.findOne(new Query(), Home.class);
        if (h != null && !h.getNewsDtoList().isEmpty()) {
            List<NewDto> list = h.getNewsDtoList();
            for (int i = 0; i < list.size(); i++) {
                if (slug.equals(list.get(i).getSlug())) {
                    NewDto dto = NewDto.builder()
                            .createdAt(saved.getCreatedAt())
                            .isFeatured(saved.getIsFeatured())
                            .header(saved.getHeader())
                            .slug(saved.getSlug())
                            .build();
                    list.set(i, dto);
                    break;
                }
            }
            h.setNewsDtoList(list);
            temp.save(h, "Home");
        }

        return saved;
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
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        Home h = temp.findOne(new Query(), Home.class);
        if (page != null && page.getNewsDtoList() != null && !page.getNewsDtoList().isEmpty()) {
            List<NewDto> list = page.getNewsDtoList();
            boolean removed = list.removeIf(dto -> slug.equals(dto.getSlug()));
            if (removed) {
                page.setNewsDtoList(list);
                temp.save(page, "NewsPage");
            }
        }
        if (h != null && h.getNewsDtoList() != null && !h.getNewsDtoList().isEmpty()) {
            List<NewDto> list = h.getNewsDtoList();
            boolean removed = list.removeIf(dto -> slug.equals(dto.getSlug()));
            if (removed) {
                h.setNewsDtoList(list);
                temp.save(h, "Home");
            }
        }

        return "Deleted Successfully";
    }


}
