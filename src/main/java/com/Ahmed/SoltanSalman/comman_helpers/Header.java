package com.Ahmed.SoltanSalman.comman_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    private Title title;
    private Description desc;
    private String imgUrl;
}
