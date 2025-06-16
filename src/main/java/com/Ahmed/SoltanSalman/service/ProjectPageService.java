package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ProjectPageService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;

    public ProjectPage getProjectPage(){
        Query q=new Query();
        ProjectPage projectPage=temp.findOne(q , ProjectPage.class);
        if(projectPage==null) throw new NoSuchElementException("Not Found");
        return projectPage;
    }

    public ProjectPage updateProjectPageHeader(Header headerRequest , MultipartFile  file){
        if (headerRequest == null && (file == null || file.isEmpty())) {
            throw new IllegalArgumentException("At least one of headerRequest or file must be provided");
        }
        ProjectPage projectPage=temp.findOne(new Query() , ProjectPage.class);
        if (projectPage == null) {
            throw new NoSuchElementException("ProjectPage not found");
        }
        if(headerRequest!=null){
            if (headerRequest.getTitle() != null) {
                    projectPage.getHeader().setTitle(headerRequest.getTitle());
                }
                if (headerRequest.getDesc() != null) {
                    projectPage.getHeader().setDesc(headerRequest.getDesc());
                }
        }
        if(file!=null && !file.isEmpty()){
            Map<String, Object> options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "timestamp", System.currentTimeMillis() / 1000
            );
            try{
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
                String url = uploadResult.get("secure_url").toString();
                projectPage.getHeader().setImgUrl(url);
            }catch(Exception e){
                throw new RuntimeException("process can not be completed");
            }
        }

            return temp.save(projectPage , "ProjectPage");
       }
}
