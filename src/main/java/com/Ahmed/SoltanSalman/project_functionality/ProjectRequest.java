package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private Header header;
    private String imageBase64;
    private Overview overview;
    private List<Objective> objectives;
    private List<Achievement> achievements;
    private List<Employee> team;
    private Specification specification;
    private State state;
    private Boolean isInvestment;
}
