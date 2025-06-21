package com.Ahmed.SoltanSalman.contact_with_us_functionality;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ComplaintService {
    private final MongoTemplate temp;
    private final EmailService service;
    public Complaint addComplaint(ComplaintRequest request){
       Complaint c= temp.save(Complaint.builder()
                .fullName(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build() , "ContactPost");
       service.sendComplaintConfirmation(request.getEmail() , request.getName());
       return c;
    }
}
