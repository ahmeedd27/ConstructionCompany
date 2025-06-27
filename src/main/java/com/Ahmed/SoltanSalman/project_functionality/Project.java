package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.Ahmed.SoltanSalman.comman_helpers.Image;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
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
    @JsonSerialize(using = ToStringSerializer.class) @Id @Field("_id")
    private ObjectId _id;
    private String slug;
    private Header header;
    private Overview overview;
    private List<Objective> objectives;
    private List<Achievement> achievements;
    private List<Employee> team;
    private Specification specification;
    private List<Image> images;
    private State state;
    private Date createdAt;
}
