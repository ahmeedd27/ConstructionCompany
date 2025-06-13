package com.Ahmed.SoltanSalman.home_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area {
    private String title;
    private String desc;
    private String imgUrl;
    private String header;
    private String text;
    private List<AreaInfo> info;
}
