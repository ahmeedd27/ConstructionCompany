package com.Ahmed.SoltanSalman.home_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaInfo {
    @Field("id")
    private int id;
    private String title;
    private String text;
}
