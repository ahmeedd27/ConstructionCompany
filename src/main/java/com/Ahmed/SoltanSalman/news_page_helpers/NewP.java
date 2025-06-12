package com.Ahmed.SoltanSalman.news_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewP {
    private String _id;
    private String slug;
    private String title;
    private String desc;
    private String imgUrl;
    private boolean isFeatured;
    private String category;
}
