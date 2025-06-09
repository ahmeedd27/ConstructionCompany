package com.Ahmed.SoltanSalman.projectpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ProjectPage")
public class ProjectPage {
    private ObjectId _id;
    private Header header;
    private List<Achievement> achievements;
    private List<ProjectP> projects;
}
