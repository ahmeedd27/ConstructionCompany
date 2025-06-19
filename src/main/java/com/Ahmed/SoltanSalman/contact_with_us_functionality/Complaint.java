package com.Ahmed.SoltanSalman.contact_with_us_functionality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "ContactPost")
public class Complaint {
    private String _id;
    private String fullName;
    private String email;
    private String subject;
    private String message;
}
