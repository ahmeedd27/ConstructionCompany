package com.Ahmed.SoltanSalman.comman_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private int id;
    private String imgUrl;
    private Title title;
}
