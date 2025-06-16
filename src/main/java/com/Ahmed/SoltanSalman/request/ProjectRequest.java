package com.Ahmed.SoltanSalman.request;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import com.Ahmed.SoltanSalman.global_helpers.Title;
import com.Ahmed.SoltanSalman.projects_helpers.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private Header header;
    private Overview overview;
    private List<Objective> objectives;
    private List<Achievement> achievements;
    private List<Employee> team;
    private Specification specification;
    private State state;
    private ProjectCategory category;
    private List<ImageUpload> images;
}
