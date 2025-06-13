package com.Ahmed.SoltanSalman.home_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {
    private String title;
    private String desc;
    private List<String> imgUrls;
    private String linkVid;
}
