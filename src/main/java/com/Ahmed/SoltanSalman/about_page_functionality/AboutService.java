package com.Ahmed.SoltanSalman.about_page_functionality;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
public class AboutService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;

    public About getAboutPage() {
        About a = temp.findOne(new Query(), About.class);
        if (a == null) throw new NoSuchElementException();
        return a;
    }

    public About updateAboutData(AboutUpdateRequest request) {
        if (request == null) throw new IllegalArgumentException();
        About page = temp.findOne(new Query(), About.class);
        if (page == null) throw new NoSuchElementException("No Page");
        if (request.getHeaderTitle() != null) {
            if (request.getHeaderTitle().getEn() != null)
                page.getHeader().getTitle().setEn(request.getHeaderTitle().getEn());

            if (request.getHeaderTitle().getAr() != null)
                page.getHeader().getTitle().setAr(request.getHeaderTitle().getAr());
        }
        if (request.getHeaderDesc() != null) {
            if (request.getHeaderDesc().getEn() != null)
                page.getHeader().getDesc().setEn(request.getHeaderDesc().getEn());

            if (request.getHeaderDesc().getAr() != null)
                page.getHeader().getDesc().setAr(request.getHeaderDesc().getAr());
        }
        if (request.getHeaderImageBase64() != null && !request.getHeaderImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            String url = "";
            String base64Data = request.getHeaderImageBase64().split(",")[1];
            try {
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                url = uploadResult.get("secure_url").toString();
                page.getHeader().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        if (request.getStoryTitle() != null) {
            if (request.getStoryTitle().getEn() != null)
                page.getOurStory().getTitle().setEn(request.getStoryTitle().getEn());

            if (request.getStoryTitle().getAr() != null)
                page.getOurStory().getTitle().setAr(request.getStoryTitle().getAr());
        }
        if (request.getStoryDesc() != null) {
            if (request.getStoryDesc().getEn() != null)
                page.getOurStory().getDesc().setEn(request.getStoryDesc().getEn());

            if (request.getStoryDesc().getAr() != null)
                page.getOurStory().getDesc().setAr(request.getStoryDesc().getAr());
        }
        if (request.getStoryImageBase64() != null && !request.getStoryImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            String url = "";
            String base64Data = request.getStoryImageBase64().split(",")[1];
            try {
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                url = uploadResult.get("secure_url").toString();
                page.getOurStory().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        if (request.getMembers() != null && !request.getMembers().isEmpty()) {
            page.setMainMembers(request.getMembers());
        }
        return temp.save(page, "About");
    }

}
