package com.Ahmed.SoltanSalman.newspage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    private String title;
    private String desc;
    private String imgUrl;
}
