package com.Ahmed.SoltanSalman.project_functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

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
