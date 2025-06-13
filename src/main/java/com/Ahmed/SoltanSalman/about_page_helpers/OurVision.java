package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OurVision {
    @Field("title")
    private String title;
    private String desc;
    private List<VisionItem> visionList;
}
