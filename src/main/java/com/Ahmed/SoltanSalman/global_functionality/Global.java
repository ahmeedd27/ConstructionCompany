package com.Ahmed.SoltanSalman.global_functionality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Global")
public class Global {
    @Id
    private String _id;
    private String logo;
    private SocialMedia socialMedia;
    private String address;
    private String phone;
    private String email;
    private String workingHours;

}
