package com.Ahmed.SoltanSalman.contact_us_page_functionality;


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

@RequiredArgsConstructor
@Service
public class ContactUsService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    public Contact getContactUsPage(){
        Contact c=temp.findOne(new Query(), Contact.class);
        if(c==null) throw new NoSuchElementException();
        return c;
    }
    public Contact updateContactUsPage(ContactUsUpdateRequest request){
        Contact c=temp.findOne(new Query(), Contact.class);
        if(c==null) throw new NoSuchElementException();

        if (request.getTitle() != null) {
            if (request.getTitle().getEn() != null)
                c.getHeader().getTitle().setEn(request.getTitle().getEn());

            if (request.getTitle().getAr() != null)
                c.getHeader().getTitle().setAr(request.getTitle().getAr());
        }

        if (request.getDescription() != null) {
            if (request.getDescription().getEn() != null)
                c.getHeader().getDesc().setEn(request.getDescription().getEn());

            if (request.getDescription().getAr() != null)
                c.getHeader().getDesc().setAr(request.getDescription().getAr());
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
               c.getHeader().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
            }
        }
        if(request.getReceiverEmail()!=null){
            c.setReceiverEmail(request.getReceiverEmail());
        }
        temp.save(c, "Contact Us");
        return c;
    }

}
