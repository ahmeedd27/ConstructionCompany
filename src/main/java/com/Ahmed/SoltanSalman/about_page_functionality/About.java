package com.Ahmed.SoltanSalman.about_page_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "About")
public class About {
    @JsonSerialize(using = ToStringSerializer.class) @Id
    @Field("_id")
    private ObjectId _id;
    private Header header;
    private Header ourStory;
    private List<MainMembers> mainMembers;

}
