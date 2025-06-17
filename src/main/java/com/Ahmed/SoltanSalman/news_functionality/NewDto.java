package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewDto {
    private String _id;
    private String slug;
    private Header header;
    private Date createdAt;
    private NewsCategory category;
    private Boolean isFeatured;
}
