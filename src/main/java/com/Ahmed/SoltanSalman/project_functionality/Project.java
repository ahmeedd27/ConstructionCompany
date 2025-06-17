package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import com.Ahmed.SoltanSalman.global_helpers.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Projects")
public class Project {
    @Field("_id")
    @Id
    private String _id;
    private String slug;
    private Header header;
    private Overview overview;
    private List<Objective> objectives;
    private List<Achievement> achievements;
    private List<Employee> team;
    private Specification specification;
    private List<Image> images;
    private State state;
    private ProjectCategory category;
    private Date createdAt;
}
