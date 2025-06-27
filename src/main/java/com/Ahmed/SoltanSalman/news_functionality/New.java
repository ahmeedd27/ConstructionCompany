package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "News")
public class New {
    @JsonSerialize(using = ToStringSerializer.class) @Id @Field("_id")
    private ObjectId _id;
    private String slug;
    private Header header;
    private Date createdAt;
    private Article article;
    private Boolean isFeatured;
}
