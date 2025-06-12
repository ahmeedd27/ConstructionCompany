package com.Ahmed.SoltanSalman.global_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Global")
public class Global {
    private String logo;
    private String x;
    private String facebook;
    private String linkedin;
    private String instagram;
    private String address;
    private String phone;
    private String email;
    private String workingHours;

}
