package com.Ahmed.SoltanSalman.project_functionality;


import com.Ahmed.SoltanSalman.comman_helpers.COARequest;
import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.Ahmed.SoltanSalman.comman_helpers.Image;
import com.Ahmed.SoltanSalman.comman_helpers.ImageUpload;
import com.Ahmed.SoltanSalman.home_functionality.Home;
import com.Ahmed.SoltanSalman.news_functionality.NewDto;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
        if (deleteResult.getDeletedCount() == 0) throw new IllegalArgumentException();

        ProjectPage page = temp.findOne(new Query(), ProjectPage.class);
        Home h=temp.findOne(new Query() , Home.class);

        if (page != null && page.getProjects() != null && !page.getProjects().isEmpty()) {
            List<ProjectDTO> list = page.getProjects();
            boolean removed = list.removeIf(dto -> slug.equals(dto.getSlug()));
            if (removed) {
                page.setProjects(list);
                temp.save(page, "ProjectPage");
            }
        }
        if(h!=null && h.getProjects()!=null && !h.getProjects().isEmpty()){
            List<ProjectDTO> list = h.getProjects();
            boolean removed = list.removeIf(dto -> slug.equals(dto.getSlug()));
            if (removed) {
                h.setProjects(list);
                temp.save(h, "Home");
            }
        }
        return "Project deleted successfully.";

    }

    public List<Employee> getAllEmployees() {
        Query query = new Query();
        List<Employee> employees = temp.findAll(Employee.class);
        if (employees.isEmpty()) {
            throw new NoSuchElementException("No Elements");
        }
        return employees;
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

    public Project addProjectWithImages(CreateProjectRequest request) {

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
                   int count=1;
                    String base64Data = imageUpload.getImageFile().split(",")[1];
                    byte[] fileData = Base64.getDecoder().decode(base64Data);
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(fileData, options);
                    String url = uploadResult.get("secure_url").toString();
                    imgUrls.add(url);
                    img.add(new Image(count++ ,url, imageUpload.getTitle()));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to upload image: " + e.getMessage());
                }
            }

        }
        String generatedSlug = utils.generateSlug(
                request.getTitle().getEn() != null ?
                        request.getTitle().getEn() : "project");


        List<Employee> emps = new ArrayList<>();
        for (Employee e : request.getTeam()) {
            Employee processed = utils.processEmployee(e);
            if (processed != null) {
                emps.add(processed);
            } else {
                throw new NoSuchElementException("Employee not found with id: " + e.get_id());
            }
        }
        Header header = new Header(request.getTitle(), request.getDesc(), imgUrls.get(0));
        Project savedProject = temp.save(
                Project.builder()
                        .slug(generatedSlug)
                        .header(header)
                        .overview(request.getOverview())
                        .objectives(utils.processObjective(request.getObjectives()))
                        .achievements(utils.processAchievement(request.getAchievements()))
                        .team(emps)
                        .specification(request.getSpecification())
                        .state(utils.processState(request.getStateInEnglish()))
                        .images(img)
                        .createdAt(new Date())
                        .build(), "Projects"
        );
        ProjectDTO projectDTO = ProjectDTO.builder()
                ._id(savedProject.get_id())
                .slug(savedProject.getSlug())
                .imgUrl(savedProject.getHeader().getImgUrl())
                .state(savedProject.getState())
                .title(savedProject.getHeader().getTitle())
                .desc(savedProject.getHeader().getDesc())
                .createdAt(savedProject.getCreatedAt())
                .build();

        ProjectPage page = temp.findOne(new Query(), ProjectPage.class);
        if (page != null) {
            page.getProjects().add(projectDTO);
            temp.save(page, "ProjectPage");
        }
        Home h = temp.findOne(new Query(), Home.class);
        if (h != null) {
            h.getProjects().addFirst(projectDTO);
            h.getProjects().removeLast();
            temp.save(h, "Home");
        }
        return savedProject;
    }


    public Project updateProject(String slug, ProjectRequest request) {
        Query q = new Query();
        q.addCriteria(Criteria.where("slug").is(slug));
        Project existing = temp.findOne(q, Project.class);
        if (existing == null) throw new NoSuchElementException("Project not found");
        if (request.getHeader() != null) {
            if (request.getHeader().getTitle() != null) {
                if (request.getHeader().getTitle().getEn() != null)
                    existing.getHeader().getTitle().setEn(request.getHeader().getTitle().getEn());

                if (request.getHeader().getTitle().getAr() != null)
                    existing.getHeader().getTitle().setAr(request.getHeader().getTitle().getAr());
            }
            if (request.getHeader().getDesc() != null) {
                if (request.getHeader().getDesc().getEn() != null)
                    existing.getHeader().getDesc().setEn(request.getHeader().getDesc().getEn());

                if (request.getHeader().getDesc().getAr() != null)
                    existing.getHeader().getDesc().setAr(request.getHeader().getDesc().getAr());
            }
        }
        if (request.getOverview() != null) {
            if (request.getOverview().getEn() != null) {
                existing.getOverview().setEn(request.getOverview().getEn());
            }
            if (request.getOverview().getAr() != null) {
                existing.getOverview().setAr(request.getOverview().getAr());
            }
        }

        if (request.getObjectives() != null)
            existing.setObjectives(request.getObjectives());
        if (request.getAchievements() != null)
            existing.setAchievements(request.getAchievements());

        if (request.getSpecification() != null) {
            if (request.getSpecification().getArea() != null) {
                existing.getSpecification().setArea(request.getSpecification().getArea());
            }
            if (request.getSpecification().getDuration() != null) {
                existing.getSpecification().setDuration(request.getSpecification().getDuration());
            }

        }
        if (request.getState() != null) {
            if (request.getState().getEn() != null) {
                existing.getState().setEn(request.getState().getEn());
                existing.getState().setAr(utils.processState(request.getState().getEn()).getAr());
            }
            if (request.getState().getAr() != null) {
                existing.getState().setAr(request.getState().getAr());
                existing.getState().setEn(utils.processState(request.getState().getAr()).getEn());
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
                        int count=1;
                        byte[] decoded = Base64.getDecoder().decode(imgReq.getImageFile().split(",")[1]);
                        Map<String, Object> options = ObjectUtils.asMap(
                                "resource_type", "image",
                                "timestamp", System.currentTimeMillis() / 1000
                        );
                        Map<?, ?> uploadResult = cloudinary.uploader().upload(decoded, options);
                        String imgUrl = uploadResult.get("secure_url").toString();
                        finalImgs.add(new Image(count++ ,imgUrl, imgReq.getTitle()));
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
                            .createdAt(saved.getCreatedAt())
                            .build();
                    list.set(i, updatedDTO);
                    break;
                }
            }
            page.setProjects(list);
            temp.save(page, "ProjectPage");
        }

        //update home update
       Home h = temp.findOne(new Query(), Home.class);
        if (h != null && h.getProjects() != null) {
            List<ProjectDTO> list = h.getProjects();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSlug().equals(slug)) {
                    ProjectDTO updatedDTO = ProjectDTO.builder()
                            ._id(saved.get_id())
                            .slug(saved.getSlug())
                            .imgUrl(saved.getHeader().getImgUrl())
                            .state(saved.getState())
                            .title(saved.getHeader().getTitle())
                            .desc(saved.getHeader().getDesc())
                            .createdAt(saved.getCreatedAt())
                            .build();
                    list.set(i, updatedDTO);
                    break;
                }
            }
            h.setProjects(list);
            temp.save(h, "Home");
        }

        return saved;
    }


}
