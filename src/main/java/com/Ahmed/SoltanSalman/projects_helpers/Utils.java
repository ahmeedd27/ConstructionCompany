package com.Ahmed.SoltanSalman.projects_helpers;

import com.Ahmed.SoltanSalman.global_helpers.Title;
import com.Ahmed.SoltanSalman.request.ProjectRequest;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Getter
public class Utils {

    private final MongoTemplate temp;
    private final Cloudinary cloudinary;
    private final Random random;

    public Employee processEmployee(Employee request) {
        if (request.get_id() != null) {
            Query q = new Query();
            q.addCriteria(Criteria.where("_id").is(request.get_id()));
            return temp.findOne(q, Employee.class);
        } else if (request.getName() == null || request.getJobTitle() == null ||
                request.getJobTitle().getAr() == null || request.getJobTitle().getEn() == null ||
                request.getName().getAr() == null || request.getName().getEn() == null) {
            throw new IllegalArgumentException("Not Valid");
        } else {
            return temp.save(request, "Employees");
        }

    }

    public ProjectCategory processCategory(ProjectCategory request) {
        if (request.get_id() != null) {
            Query q = new Query();
            q.addCriteria(Criteria.where("_id").is(request.get_id()));
            if (temp.findOne(q, ProjectCategory.class) != null) {
                return temp.findOne(q, ProjectCategory.class);
            } else {
                throw new NoSuchElementException("Not Found");
            }
        } else if (request.getAr() == null || request.getEn() == null) {
            throw new IllegalArgumentException("Not Valid");
        } else {
            return temp.save(request, "Categories");
        }
    }
    public String generateSlug(String baseName) {
        if (baseName == null || baseName.trim().isEmpty()) {
            baseName = "project";
        }
        // تحويل إلى kebab-case وإزالة الرموز غير المسموح بها
        String sanitized = baseName.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "") // إزالة الرموز غير المسموح بها
                .replaceAll("\\s+", "-"); // استبدال المسافات بـ -
        // إضافة رقم عشوائي من 100 إلى 999
        int randomNum = random.nextInt(900) + 100;
        return sanitized + "-" + randomNum;
    }

    public State processState(State s) {
        if (s.getAr() == null) {
            if (s.getEn().equals("IN_PROGRESS")) s.setAr("قيد التنفيذ");
            else if (s.getEn().equals("PLANNING")) s.setAr("يتم التخطيط");
            else if (s.getEn().equals("DONE")) s.setAr("تم");
        }
        return s;
    }

    public void updateProjectFields(Project project, ProjectRequest request) {

        if (request.getHeader() != null) {
            if(request.getHeader().getTitle()!=null){
                project.getHeader().setTitle(request.getHeader().getTitle());
            }
            if(request.getHeader().getDesc()!=null){
                project.getHeader().setDesc(request.getHeader().getDesc());
            }

        }


        if (request.getOverview() != null) {
            project.setOverview(request.getOverview());
        }


        if (request.getObjectives() != null && !request.getObjectives().isEmpty()) {
            project.setObjectives(request.getObjectives());
        }

        if (request.getAchievements() != null && !request.getAchievements().isEmpty()) {
            project.setAchievements(request.getAchievements());
        }


        if (request.getSpecification() != null) {
            if(request.getSpecification().getArea()!=null){
                project.getSpecification().setArea(request.getSpecification().getArea());
            }
            if(request.getSpecification().getDuration()!=null){
                project.getSpecification().setDuration(request.getSpecification().getDuration());
            }

        }


        if (request.getState() != null) {
            if(request.getState().getEn()!=null){
                project.getState().setEn(request.getState().getEn());
            }
            if(request.getState().getAr()!=null){
                project.getState().setAr(request.getState().getAr());
            }

        }


        if (request.getCategory() != null) {
            if(request.getCategory().getEn()!=null){
                project.getCategory().setEn(request.getCategory().getEn());
            }
            if(request.getCategory().getAr()!=null){
                project.getCategory().setAr(request.getCategory().getAr());
            }
        }


        if (request.getTeam() != null && !request.getTeam().isEmpty()) {
            List<Employee> updatedTeam = new ArrayList<>();
            for (Employee emp : request.getTeam()) {
                Employee processed = processEmployee(emp);
                if (processed != null) {
                    updatedTeam.add(processed);
                } else {
                    throw new NoSuchElementException("Employee not found with id: " + emp.get_id());
                }
            }
            project.setTeam(updatedTeam);
        }
    }
    public List<Achievement> processAchievement(List<Achievement> achievements){
        List<Achievement> l=new ArrayList<>();
        for(Achievement a:achievements){
            l.add(new Achievement( random.nextInt(900) + 100,a.getEn() , a.getAr()));
        }
        return l;
    }
    public List<Objective> processObjective(List<Objective> objectives){
        List<Objective> l=new ArrayList<>();
        for(Objective a:objectives){
            l.add(new Objective ( random.nextInt(900) + 100,a.getEn() , a.getAr()));
        }
        return l;
    }


}
