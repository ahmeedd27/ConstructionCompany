package com.Ahmed.SoltanSalman.project_functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
    @Field("id")
    private int id;
    private String ar;
    private String en;
}
