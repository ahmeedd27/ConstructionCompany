package com.Ahmed.SoltanSalman.project_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectP {
    private String _id;
    private String slug;
    private String imgUrl;
    private String state;
    private String title;
    private String desc;
    private String category;
}
