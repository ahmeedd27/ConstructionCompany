package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OurStory {
    private String title;
    private String text;
    private String imgUrl;
    private List<Property> properties;
}
