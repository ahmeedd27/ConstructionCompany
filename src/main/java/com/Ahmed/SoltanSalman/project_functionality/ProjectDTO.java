package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Description;
import com.Ahmed.SoltanSalman.global_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
