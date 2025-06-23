package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest {
    private Title title;
    private Description desc;
    private String headerImageBase64;
    private Overview overview;
    private List<Objective> objectives;
    private List<Achievement> achievements;
    private List<Employee> team;
    private Specification specification;
    private String imageBase64;
    private Title imageTitle;
    private State state;
    private ProjectCategory category;
}
