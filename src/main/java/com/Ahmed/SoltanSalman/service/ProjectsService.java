package com.Ahmed.SoltanSalman.service;


import com.Ahmed.SoltanSalman.project_page_helpers.ProjectDTO;
import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import com.Ahmed.SoltanSalman.projects_helpers.Utils;
import com.Ahmed.SoltanSalman.projects_helpers.*;
import com.Ahmed.SoltanSalman.request.CategoryRequest;
import com.Ahmed.SoltanSalman.request.EmployeeRequest;
import com.Ahmed.SoltanSalman.request.ImageUpload;
import com.Ahmed.SoltanSalman.request.ProjectRequest;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ProjectsService {
    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    private final Utils utils;

    public Project getProjectBySlug(String slug) {
        if (slug == null || slug.isEmpty()) {
            throw new IllegalArgumentException("Slug cannot be null or empty");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("slug").is(slug));
        Project project = temp.findOne(query, Project.class);
        if (project == null) {
            throw new NoSuchElementException("Project not found with slug: " + slug);
        }
        return project;
    }

    public List<Project> getAllProjects() {
        Query query = new Query();
        List<Project> projects = temp.findAll(Project.class);
        if (projects.isEmpty()) {
            throw new NoSuchElementException("No Elements");
        }
        return projects;
    }

    public String deleteProjectBySlug(String slug) {
        if (slug == null || slug.isEmpty()) {
            throw new IllegalArgumentException("Slug cannot be null or empty");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("slug").is(slug));
        DeleteResult deleteResult = temp.remove(query, Project.class);
        List<ProjectDTO> d = new ArrayList<>();
        if (deleteResult.getDeletedCount() > 0) {
            ProjectPage page = temp.findOne(new Query(), ProjectPage.class);
            if (page.getProjects() != null) {
                for (int i = 0; i < Math.min(page.getProjects().size(), temp.findAll(Project.class).size()); i++) {
                    if (!page.getProjects().get(i).getSlug().equals(slug)) {
                        d.add(page.getProjects().get(i));
                    }
                }
                page.setProjects(d);
                temp.save(page, "ProjectPage");
            }
            return "Project deleted successfully.";
        } else {
            throw new NoSuchElementException("Project not found with slug: " + slug);
        }
    }

    public List<Employee> getAllEmployees() {
        Query query = new Query();
        List<Employee> employees = temp.findAll(Employee.class);
        if (employees.isEmpty()) {
            throw new NoSuchElementException("No Elements");
        }
        return employees;
    }

    public List<ProjectCategory> getAllCategories() {
        Query query = new Query();
        List<ProjectCategory> categories = temp.findAll(ProjectCategory.class);
        if (categories.isEmpty()) {
            throw new NoSuchElementException("No Elements");
        }
        return categories;
    }

    public Employee addEmployee(EmployeeRequest employee) {
        if (employee != null) {
            Employee e = new Employee(
                    employee.getName(),
                    employee.getJobTitle()
            );
            return temp.save(e, "Employees");

        } else {
            throw new IllegalArgumentException("this operation can not be done");
        }
    }

    public ProjectCategory addCategory(CategoryRequest category) {
        if (category != null) {
            ProjectCategory c = new ProjectCategory(
                    category.getAr(),
                    category.getEn()
            );
            return temp.save(c, "Categories");

        } else {
            throw new IllegalArgumentException("this operation can not be done");
        }
    }


    public Project addProjectWithImages(ProjectRequest request) {
        if (request == null) throw new IllegalArgumentException("no valid");

        List<String> imgUrls = new ArrayList<>();
        Map<String, Object> options = ObjectUtils.asMap(
                "resource_type", "image",
                "timestamp", System.currentTimeMillis() / 1000
        );

        List<Image> img = new ArrayList<>();
        List<ImageUpload> imageUploads = request.getImages();

        if (imageUploads != null && !imageUploads.isEmpty()) {
            for (ImageUpload imageUpload : imageUploads) {
                try {
                    // إزالة الـ "data:image/..." prefix
                    String base64Data = imageUpload.getImageFile().split(",")[1];
                    byte[] fileData = Base64.getDecoder().decode(base64Data);
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                    String url = uploadResult.get("secure_url").toString();
                    imgUrls.add(url);
                    img.add(new Image(url, imageUpload.getTitle()));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to upload image: " + e.getMessage());
                }
            }
            // أول صورة هتكون imgUrl للـ header
            request.getHeader().setImgUrl(imgUrls.get(0));
        }

        List<Employee> emps = new ArrayList<>();
        for (Employee e : request.getTeam()) {
            Employee processed = utils.processEmployee(e);
            if (processed != null) {
                emps.add(processed);
            } else {
                throw new NoSuchElementException("Employee not found with id: " + e.get_id());
            }
        }

        String generatedSlug = utils.generateSlug(
                request.getHeader().getTitle().getEn() != null ?
                        request.getHeader().getTitle().getEn() : "project");

        Project savedProject = temp.save(Project.builder()
                .slug(generatedSlug)
                .header(request.getHeader())
                .overview(request.getOverview())
                .objectives(utils.processObjective(request.getObjectives()))
                .achievements(utils.processAchievement(request.getAchievements()))
                .specification(request.getSpecification())
                .team(emps)
                .images(img)
                .state(utils.processState(request.getState()))
                .category(utils.processCategory(request.getCategory()))
                .createdAt(new Date())
                .build(), "Projects");

        // ضيف في الصفحة الرئيسية
        ProjectDTO projectDTO = ProjectDTO.builder()
                ._id(savedProject.get_id())
                .slug(savedProject.getSlug())
                .imgUrl(savedProject.getHeader().getImgUrl())
                .state(savedProject.getState())
                .title(savedProject.getHeader().getTitle())
                .desc(savedProject.getHeader().getDesc())
                .category(savedProject.getCategory())
                .createdAt(savedProject.getCreatedAt())
                .build();

        ProjectPage page = temp.findOne(new Query(), ProjectPage.class);
        if (page != null) {
            page.getProjects().add(projectDTO);
            temp.save(page, "ProjectPage");
        }

        return savedProject;
    }


    public Project updateProject(String slug, ProjectRequest request) {
        Query q = new Query();
        q.addCriteria(Criteria.where("slug").is(slug));
        Project existing = temp.findOne(q, Project.class);
        if (existing == null) throw new NoSuchElementException("Project not found");
        if (request.getHeader() != null) {
            if (request.getHeader().getTitle() != null)
                existing.getHeader().setTitle(request.getHeader().getTitle());
            if (request.getHeader().getDesc() != null)
                existing.getHeader().setDesc(request.getHeader().getDesc());
        }
        if (request.getOverview() != null)
            existing.setOverview(request.getOverview());
        if (request.getObjectives() != null)
            existing.setObjectives(utils.processObjective(request.getObjectives()));
        if (request.getAchievements() != null)
            existing.setAchievements(utils.processAchievement(request.getAchievements()));

        if (request.getSpecification() != null) {
            if (request.getSpecification().getArea() != null) {
                request.getSpecification().setArea(request.getSpecification().getArea());
            }
            if (request.getSpecification().getDuration() != null) {
                request.getSpecification().setDuration(request.getSpecification().getDuration());
            }

        }
        if (request.getState() != null) {
            if (request.getState().getEn() != null) {
                request.getState().setEn(request.getState().getEn());
            }
            if (request.getState().getAr() != null) {
                request.getState().setAr(request.getState().getAr());
            }

        }
        if (request.getCategory() != null) {
            if (request.getCategory().getEn() != null) {
                request.getCategory().setEn(request.getCategory().getEn());
            }
            if (request.getCategory().getAr() != null) {
                request.getCategory().setAr(request.getCategory().getAr());
            }
        }
        if (request.getTeam() != null) {
            List<Employee> emps = new ArrayList<>();
            for (Employee e : request.getTeam()) {
                emps.add(utils.processEmployee(e));
            }
            existing.setTeam(emps);
        }
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            List<Image> finalImgs = new ArrayList<>();
            for (ImageUpload imgReq : request.getImages()) {
                if (imgReq.getImageFile() != null && !imgReq.getImageFile().isEmpty()) {
                    try {
                        byte[] decoded = Base64.getDecoder().decode(imgReq.getImageFile().split(",")[1]);
                        Map<String, Object> options = ObjectUtils.asMap(
                                "resource_type", "image",
                                "timestamp", System.currentTimeMillis() / 1000
                        );
                        Map<?, ?> uploadResult = cloudinary.uploader().upload(decoded, options);
                        String imgUrl = uploadResult.get("secure_url").toString();
                        finalImgs.add(new Image(imgUrl, imgReq.getTitle()));
                    } catch (Exception e) {
                        throw new RuntimeException("Image upload failed");
                    }
                } else {
                    // No new image, keep existing or skip
                    throw new IllegalArgumentException("Missing base64 for new image");
                }
            }

            // Update main image in header
            if (!finalImgs.isEmpty()) {
                existing.getHeader().setImgUrl(finalImgs.get(0).getImgUrl());
            }

            existing.setImages(finalImgs); // or merge instead of replace
        }

        Project saved = temp.save(existing, "Projects");

        //  Update ProjectPage
        ProjectPage page = temp.findOne(new Query(), ProjectPage.class);
        if (page != null && page.getProjects() != null) {
            List<ProjectDTO> list = page.getProjects();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSlug().equals(slug)) {
                    ProjectDTO updatedDTO = ProjectDTO.builder()
                            ._id(saved.get_id())
                            .slug(saved.getSlug())
                            .imgUrl(saved.getHeader().getImgUrl())
                            .state(saved.getState())
                            .title(saved.getHeader().getTitle())
                            .desc(saved.getHeader().getDesc())
                            .category(saved.getCategory())
                            .createdAt(saved.getCreatedAt())
                            .build();
                    list.set(i, updatedDTO);
                    break;
                }
            }
            page.setProjects(list);
            temp.save(page, "ProjectPage");
        }

        return saved;
    }


}
