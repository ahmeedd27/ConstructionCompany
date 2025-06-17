package com.Ahmed.SoltanSalman.news_functionality;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsPageUpdateRequest {
    private Header header;
    private String imageBase64;
}
