package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ProjectPage")
public class ProjectPage {
    private String _id;
    private Header header;
    private List<ProjectDTO> projects;


}
