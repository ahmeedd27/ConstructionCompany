package com.Ahmed.SoltanSalman.global_functionality;

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
public class GlobalService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    public Global getGlobalData(){
        Global g= temp.findOne(new Query() , Global.class);
        if(g==null) throw new NoSuchElementException();
        return g;
    }
    public Global updateGlobalData(GlobalUpdateRequest request){
        Global g= temp.findOne(new Query() , Global.class);
        if(g==null) throw new NoSuchElementException();
        if(request.getImageBase64() != null && !request.getImageBase64().isEmpty()){
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
              g.setLogo(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        if(request.getSocialMedia()!=null){
            if(request.getSocialMedia().getX()!=null){
                g.getSocialMedia().setX(request.getSocialMedia().getX());
            }
            if(request.getSocialMedia().getFacebook()!=null){
                g.getSocialMedia().setFacebook(request.getSocialMedia().getFacebook());
            }
            if(request.getSocialMedia().getInstagram()!=null){
                g.getSocialMedia().setInstagram(request.getSocialMedia().getInstagram());
            }
            if(request.getSocialMedia().getLinkedin()!=null){
                g.getSocialMedia().setLinkedin(request.getSocialMedia().getLinkedin());
            }
         }
        if(request.getAddress()!=null) g.setAddress(request.getAddress());
        if(request.getEmail()!=null) g.setEmail(request.getEmail());
        if (request.getPhone()!=null) g.setPhone(request.getPhone());
        if(request.getWorkingHours()!=null) g.setWorkingHours(request.getWorkingHours());
        temp.save(g,"Global");
        return g;
    }

}
