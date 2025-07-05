package com.Ahmed.SoltanSalman.project_functionality;


import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.Ahmed.SoltanSalman.home_functionality.Home;
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

    public Project addProject(CreateProjectRequest request) {


        if (request == null) throw new IllegalArgumentException("no valid");
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
        } catch (IOException e) {
            throw new RuntimeException("Image upload problem");
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
        Header header = new Header(request.getTitle(), request.getDesc(), url);
        return temp.save(
                Project.builder()
                        .slug(generatedSlug)
                        .header(header)
                        .overview(request.getOverview())
                        .objectives(utils.processObjective(request.getObjectives()))
                        .achievements(utils.processAchievement(request.getAchievements()))
                        .team(emps)
                        .specification(request.getSpecification())
                        .state(utils.processState(request.getStateInEnglish()))
                        .createdAt(new Date())
                        .isInvestment(request.isInvestment())
                        .build(), "Projects"
        );

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
                existing.getHeader().setImgUrl(url);
            } catch (IOException e) {
                throw new RuntimeException("Image upload problem");
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
        if(request.getIsInvestment()!=null){
            existing.setInvestment(request.getIsInvestment());
        }
        return temp.save(existing, "Projects");
    }


}
