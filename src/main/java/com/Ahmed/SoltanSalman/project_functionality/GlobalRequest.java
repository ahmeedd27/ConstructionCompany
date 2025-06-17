package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.global_helpers.SocialMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Global")
public class GlobalRequest {
    private SocialMedia socialMedia;
    private String address;
    private String phone;
    private String email;
    private String workingHours;

}