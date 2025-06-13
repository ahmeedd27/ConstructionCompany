package com.Ahmed.SoltanSalman.home_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutInfo {
    private String Beneficiaries;
    @Field("Completed Projects")
    private String completedProjects;
    @Field("Awards & Recognition")
    private String award;
    private String Volunteers;

}
