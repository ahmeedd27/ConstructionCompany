package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewRequest {
    private Header header;
    private Article article;
    private NewsCategory category;
    private Boolean isFeatured;
    private String imageBase64;
}
