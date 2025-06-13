package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Field("id")
    private int id;
    private String title;
    private String desc;
}
