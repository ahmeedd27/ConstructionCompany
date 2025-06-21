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
    public Home getHome(){
        Home h=temp.findOne(new Query() , Home.class);
        if(h==null) throw new NoSuchElementException();
        return h;
    }
//    public Home updateHome(HomeUpdateRequest request){
//        Home h=temp.findOne(new Query() , Home.class);
//        if(h==null) throw new NoSuchElementException();
//        if(request!=null){
//            if(request.getHeader()!=null){
//                if (request.getHeader().getTitle() != null) {
//                    if (request.getHeader().getTitle().getEn() != null)
//                        h.getHeader().getTitle().setEn(request.getHeader().getTitle().getEn());
//
//                    if (request.getHeader().getTitle().getAr() != null)
//                        h.getHeader().getTitle().setAr(request.getHeader().getTitle().getAr());
//                }
//                if (request.getHeader().getDesc() != null) {
//                    if (request.getHeader().getDesc().getEn() != null)
//                        h.getHeader().getDesc().setEn(request.getHeader().getDesc().getEn());
//
//                    if (request.getHeader().getDesc().getAr() != null)
//                        h.getHeader().getDesc().setAr(request.getHeader().getDesc().getAr());
//                }
//                if(request.getHeader().getLink()!=null){
//                    h.getHeader().setLink(request.getHeader().getLink());
//                }
//            }
//            if(request.getHeaderImageBase64()!=null && !request.getHeaderImageBase64().isEmpty()){
//                Map<String, Object> options = ObjectUtils.asMap(
//                        "resource_type", "image",
//                        "timestamp", System.currentTimeMillis() / 1000
//                );
//                String url = "";
//                String base64Data = request.getHeaderImageBase64().split(",")[1];
//                try {
//                    byte[] fileData = Base64.getDecoder().decode(base64Data);
//                    Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
//                    url = uploadResult.get("secure_url").toString();
//                    h.getHeader().getImgUrls().set(0,url);
//                } catch (IOException e) {
//                    throw new RuntimeException("Image upload problem");
//                }
//            }
//            if(request.getAboutUs()!=null){
//                if (request.getAboutUs().getTitle() != null) {
//                    if (request.getAboutUs().getTitle().getEn() != null)
//                        h.getAboutUs().getTitle().setEn(request.getAboutUs().getTitle().getEn());
//
//                    if (request.getAboutUs().getTitle().getAr() != null)
//                        h.getAboutUs().getTitle().setAr(request.getAboutUs().getTitle().getAr());
//                }
//                if (request.getAboutUs().getDesc() != null) {
//                    if (request.getAboutUs().getDesc().getEn() != null)
//                        h.getAboutUs().getDesc().setEn(request.getAboutUs().getDesc().getEn());
//
//                    if (request.getAboutUs().getDesc().getAr() != null)
//                        h.getAboutUs().getDesc().setAr(request.getAboutUs().getDesc().getAr());
//                }
//            }
//            if(request.getAboutUsImageBase64()!=null && !request.getAboutUsImageBase64().isEmpty()){
//                Map<String, Object> options = ObjectUtils.asMap(
//                        "resource_type", "image",
//                        "timestamp", System.currentTimeMillis() / 1000
//                );
//                String url = "";
//                String base64Data = request.getAboutUsImageBase64().split(",")[1];
//                try {
//                    byte[] fileData = Base64.getDecoder().decode(base64Data);
//                    Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
//                    url = uploadResult.get("secure_url").toString();
//                    h.getAboutUs().setImgUrl(url);
//                } catch (IOException e) {
//                    throw new RuntimeException("Image upload problem");
//                }
//            }
//
//
//
//        }
//    }


}
