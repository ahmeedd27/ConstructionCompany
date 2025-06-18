package com.Ahmed.SoltanSalman.news_functionality;


import com.Ahmed.SoltanSalman.comman_helpers.CategoryRequest;
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


    public NewsCategory processCategory(NewsCategory request) {
        if (request.get_id() != null) {
            Query q = new Query();
            q.addCriteria(Criteria.where("_id").is(request.get_id()));
            if (temp.findOne(q, NewsCategory.class) != null) {
                return temp.findOne(q, NewsCategory.class);
            } else {
                throw new NoSuchElementException("Not Found");
            }
        } else if (request.getAr() == null || request.getEn() == null) {
            throw new IllegalArgumentException("Not Valid");
        } else {
            return temp.save(request, "NewsCategories");
        }
    }

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


    public New addNew(NewRequest request) {
        if (request == null) throw new IllegalArgumentException("no valid");
        Map<String, Object> options = ObjectUtils.asMap(
                "resource_type", "image",
                "timestamp", System.currentTimeMillis() / 1000
        );
        String url = "";
        if (request.getImageBase64() != null && !request.getImageBase64().isEmpty()) {
            String base64Data = request.getImageBase64().split(",")[1];
            try {
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                url = uploadResult.get("secure_url").toString();
                request.getHeader().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }

        New savedNew = temp.save(New.builder()
                .slug(generateSlug(request.getHeader().getTitle().getEn()))
                .header(request.getHeader())
                .article(request.getArticle())
                .createdAt(new Date())
                .isFeatured(request.getIsFeatured())
                .category(processCategory(request.getCategory()))
                .build(), "News");
        NewDto dto = NewDto.builder()
                ._id(savedNew.get_id())
                .slug(savedNew.getSlug())
                .header(savedNew.getHeader())
                .category(savedNew.getCategory())
                .isFeatured(savedNew.getIsFeatured())
                .createdAt(savedNew.getCreatedAt())
                .build();

        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page != null) {
            page.getNewsDtoList().add(dto);
            temp.save(page, "NewsPage");
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
        if (request.getCategory() != null) {
            NewsCategory existingCategory = existed.getCategory();
            NewsCategory incomingCategory = request.getCategory();
            if (incomingCategory.getEn() != null)
                existingCategory.setEn(incomingCategory.getEn());
            if (incomingCategory.getAr() != null)
                existingCategory.setAr(incomingCategory.getAr());
            existed.setCategory(existingCategory);
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
        NewsPage page = temp.findOne(new Query(), NewsPage.class);
        if (page != null && !page.getNewsDtoList().isEmpty()) {
            List<NewDto> list = page.getNewsDtoList();
            for (int i = 0; i < list.size(); i++) {
                if (slug.equals(list.get(i).getSlug())) {
                    NewDto dto = NewDto.builder()
                            .createdAt(saved.getCreatedAt())
                            .isFeatured(saved.getIsFeatured())
                            .header(saved.getHeader())
                            .category(saved.getCategory())
                            .slug(saved.getSlug())
                            .build();
                    list.set(i, dto);
                    break;
                }
            }
            page.setNewsDtoList(list);
            temp.save(page, "NewsPage");
        }

        return saved;
    }



    public List<New> getAllNews(){
        List<New> list=temp.findAll(New.class);
        if(list.isEmpty()){
            throw new NoSuchElementException("No elements");
        }
        return list;
    }
    public New getNewBySlug(String slug){
        if(slug==null || slug.isEmpty()) throw new IllegalArgumentException("No Slug");
      Query q=new Query();
      q.addCriteria(Criteria.where("slug").is(slug));
      New n=temp.findOne(q , New.class);
      if(n==null) throw new NoSuchElementException("New not found with slug: " + slug);
      return n;
    }
    public String deleteNewBySlug(String slug){
        if(slug==null || slug.isEmpty()) throw new IllegalArgumentException("No Slug");
        Query q=new Query();
        q.addCriteria(Criteria.where("slug").is(slug));
        DeleteResult result = temp.remove(q, New.class);
        if (result.getDeletedCount() == 0) {
            throw new NoSuchElementException("No news found with the given slug");
        }
        NewsPage page=temp.findOne(new Query(), NewsPage.class);
        if(page != null && page.getNewsDtoList() != null && !page.getNewsDtoList().isEmpty()){
            List<NewDto> list=page.getNewsDtoList();
           boolean removed=list.removeIf(dto -> slug.equals(dto.getSlug()));
           if(removed){
               page.setNewsDtoList(list);
               temp.save(page , "NewsPage");
           }
        }
        return "Deleted Successfully";
    }

    public List<NewsCategory> getAllNewsCategories(){
        List<NewsCategory> categories = temp.findAll(NewsCategory.class);
        if (categories.isEmpty()) {
            throw new NoSuchElementException("No Elements");
        }
        return categories;
    }
    public NewsCategory addCategory(CategoryRequest category) {
        if (category != null) {
           NewsCategory c = new NewsCategory(
                    category.getAr(),
                    category.getEn()
            );
            return temp.save(c, "NewsCategories");

        } else {
            throw new IllegalArgumentException("this operation can not be done");
        }
    }


}
