package com.Ahmed.SoltanSalman.global_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Title;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Global")
public class Global {
    @JsonSerialize(using = ToStringSerializer.class) @Id @Field("_id")
    private ObjectId _id;
    private String logo;
    private Title address;
    private String phone;
    private String email;
    private String workingHours;

}
