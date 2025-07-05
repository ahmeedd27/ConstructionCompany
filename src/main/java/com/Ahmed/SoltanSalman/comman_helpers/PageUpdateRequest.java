package com.Ahmed.SoltanSalman.comman_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUpdateRequest {
    private Title title;
    private Description desc;
    private String imageBase64;
}
