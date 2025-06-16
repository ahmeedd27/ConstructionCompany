package com.Ahmed.SoltanSalman.project_page_helpers;

import com.Ahmed.SoltanSalman.global_helpers.Description;
import com.Ahmed.SoltanSalman.global_helpers.Title;
import com.Ahmed.SoltanSalman.projects_helpers.ProjectCategory;
import com.Ahmed.SoltanSalman.projects_helpers.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO {
    private String _id;
     private String slug;
     private String imgUrl;
    private State state;
     private Title title;
     private Description desc;
     private ProjectCategory category;
     private Date createdAt;
}
