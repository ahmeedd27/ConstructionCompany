package com.Ahmed.SoltanSalman.projects_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Employees")
public class Employee {
   // @Field("_id") @Id
    private String _id;
    private Name name;
    private JobTitle jobTitle;

    public Employee(Name name, JobTitle jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }
}
