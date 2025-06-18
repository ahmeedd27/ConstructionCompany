package com.Ahmed.SoltanSalman.comman_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUpdateRequest {
    private Header header;
    private String imageBase64;
}
