package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRequest {
    private Header header;
    private Article article;
    private Boolean isFeatured;
    private String imageBase64;
}
