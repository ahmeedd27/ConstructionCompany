package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    private String slug;
    private Header header;
    private Date createdAt;
    private Boolean isFeatured;
}
