package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {
    @Field("id")
    private int id;
    private String name;
    private String jobTitle;
    private String desc;
    private String imgUrl;
}
