package com.Ahmed.SoltanSalman.projects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Projects")
public class Project {
    private Header header;
    private String overview;
    private List<String> objectives;
    private List<String> achievements;
    private List<Team> team;
    private Specification specification;
    private List<Image> images;
    private String state;
    private String slug;

}
