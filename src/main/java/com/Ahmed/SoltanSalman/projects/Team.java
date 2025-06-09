package com.Ahmed.SoltanSalman.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private String name;
    @Field("jobtitle")
    private String jobTitle;
}
