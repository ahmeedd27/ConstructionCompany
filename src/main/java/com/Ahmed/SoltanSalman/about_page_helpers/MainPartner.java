package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainPartner {
    @Field("id")
    private int id;
    private String title;
    private String desc;
    private String imgUrl;
}
