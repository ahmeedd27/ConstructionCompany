package com.Ahmed.SoltanSalman.home_functionality;

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
public class HomeService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;

    public Home getHome() {
        Home h = temp.findOne(new Query(), Home.class);
        if (h == null) throw new NoSuchElementException();
        return h;
    }

    public Home updateHomePage(HomeUpdateRequest request) {
        Home h = temp.findOne(new Query(), Home.class);
        if (h == null) throw new NoSuchElementException();
        if (request.getHeaderTitle() != null) {
            if (request.getHeaderTitle().getAr() != null) {
                h.getHeader().getTitle().setAr(request.getHeaderTitle().getAr());
            }
            if (request.getHeaderTitle().getEn() != null) {
                h.getHeader().getTitle().setEn(request.getHeaderTitle().getEn());
            }
        }
        if (request.getHeaderDescription() != null) {
            if (request.getHeaderDescription().getAr() != null) {
                h.getHeader().getDesc().setAr(request.getHeaderDescription().getAr());
            }
            if (request.getHeaderDescription().getAr() != null) {
                h.getHeader().getDesc().setAr(request.getHeaderDescription().getAr());
            }
        }
        if (request.getLinkVid() != null) {
            h.getHeader().setLink(request.getLinkVid());
        }
        if (request.getHeaderImageBase64() != null && !request.getHeaderImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            String url = "";
            int count=0;
            for(String img:request.getHeaderImageBase64()){
                String base64Data = img.split(",")[1];
                try {
                    byte[] fileData = Base64.getDecoder().decode(base64Data);
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                    url = uploadResult.get("secure_url").toString();
                    h.getHeader().getImgUrls().set(count, url);
                    count++;
                } catch (IOException e) {
                    throw new RuntimeException("Image upload problem");
                }
            }

        }
        if (request.getAboutUsTitle() != null) {
            if (request.getAboutUsTitle().getAr() != null) {
                h.getAboutUs().getTitle().setAr(request.getAboutUsTitle().getAr());
            }
            if (request.getAboutUsTitle().getEn() != null) {
                h.getAboutUs().getTitle().setEn(request.getAboutUsTitle().getEn());
            }
        }
        if (request.getAboutUsDescription() != null) {
            if (request.getAboutUsDescription().getAr() != null) {
                h.getAboutUs().getDesc().setAr(request.getAboutUsDescription().getAr());
            }
            if (request.getAboutUsDescription().getEn() != null) {
                h.getAboutUs().getDesc().setEn(request.getAboutUsDescription().getEn());
            }
        }
        if (request.getAboutUsImageBase64() != null && !request.getAboutUsImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            String url = "";
            String base64Data = request.getAboutUsImageBase64().split(",")[1];
            try {
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                url = uploadResult.get("secure_url").toString();
                h.getAboutUs().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        if (request.getAboutAbhaTitle() != null) {
            if (request.getAboutAbhaTitle().getAr() != null) {
                h.getAboutAbha().getTitle().setAr(request.getAboutAbhaTitle().getAr());
            }
            if (request.getAboutAbhaTitle().getEn() != null) {
                h.getAboutAbha().getTitle().setEn(request.getAboutAbhaTitle().getEn());
            }
        }
        if (request.getAboutAbhaDescription() != null) {
            if (request.getAboutAbhaDescription().getAr() != null) {
                h.getAboutAbha().getDesc().setAr(request.getAboutAbhaDescription().getAr());
            }
            if (request.getAboutAbhaDescription().getEn() != null) {
                h.getAboutAbha().getDesc().setEn(request.getAboutAbhaDescription().getEn());
            }
        }
        if (request.getAboutAbhaImageBase64() != null && !request.getAboutAbhaImageBase64().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            String url = "";
            String base64Data = request.getAboutAbhaImageBase64().split(",")[1];
            try {
                byte[] fileData = Base64.getDecoder().decode(base64Data);
                Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                url = uploadResult.get("secure_url").toString();
                h.getAboutAbha().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        temp.save(h , "Home");
        return h;
    }


}
