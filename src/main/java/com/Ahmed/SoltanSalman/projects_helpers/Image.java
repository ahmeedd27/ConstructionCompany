package com.Ahmed.SoltanSalman.projects_helpers;

import com.Ahmed.SoltanSalman.global_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String imgUrl;
    private Title title;
}
