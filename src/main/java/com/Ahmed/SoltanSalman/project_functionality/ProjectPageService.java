package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.PageUpdateRequest;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
public class ProjectPageService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;

    public ProjectPageDTO getProjectPage() {
        List<Project> projects = temp.find(new Query(), Project.class, "Projects");
        List<ProjectDTO> projectDTOs = projects.stream().map(p -> ProjectDTO.builder()
                ._id(p.get_id())
                .slug(p.getSlug())
                .imgUrl(p.getHeader().getImgUrl())
                .title(p.getHeader().getTitle())
                .desc(p.getHeader().getDesc())
                .state(p.getState())
                .createdAt(p.getCreatedAt())
                .isInvestment(p.isInvestment())
                .build()).toList();
        ProjectPage projectPage = temp.findOne(new Query(), ProjectPage.class);
        if (projectPage == null) throw new NoSuchElementException("Not Found");

        return ProjectPageDTO.builder()
                .header(projectPage.getHeader())
                .projects(projectDTOs)
                .build();
    }

    public ProjectPage updateProjectPageHeader(PageUpdateRequest request) {
        ProjectPage page = temp.findOne(new Query(), ProjectPage.class);
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
        return temp.save(page, "ProjectPage");
    }
}
