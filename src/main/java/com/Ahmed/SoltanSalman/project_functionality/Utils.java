package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.COARequest;
import com.cloudinary.Cloudinary;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    public State processState(String s) {
        State ss = new State();

        if (s.equals("IN_PROGRESS")) {
            ss.setAr("قيد التنفيذ");
            ss.setEn("IN_PROGRESS");
        } else if (s.equals("PLANNING")) {
            ss.setAr("يتم التخطيط");
            ss.setEn("PLANNING");
        } else if (s.equals("DONE")) {
            ss.setAr("تم");
            ss.setEn("DONE");
        }else {
            throw new IllegalArgumentException("State is invalid: must provide a valid State");
        }

        return ss;
    }

    public List<Achievement> processAchievement(List<COARequest> achievements) {
        List<Achievement> l = new ArrayList<>();
        for (COARequest a : achievements) {
            l.add(new Achievement(random.nextInt(900) + 100, a.getEn(), a.getAr()));
        }
        return l;
    }

    public List<Objective> processObjective(List<COARequest> objectives) {
        List<Objective> l = new ArrayList<>();
        for (COARequest a : objectives) {
            l.add(new Objective(random.nextInt(900) + 100, a.getEn(), a.getAr()));
        }
        return l;
    }


}
