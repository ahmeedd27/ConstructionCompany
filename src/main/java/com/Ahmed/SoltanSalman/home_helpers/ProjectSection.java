package com.Ahmed.SoltanSalman.home_helpers;


import com.Ahmed.SoltanSalman.project_page_helpers.ProjectP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSection {
    private String title;
    private String desc;
    private List<ProjectP> projects;
}
