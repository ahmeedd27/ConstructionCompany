package com.Ahmed.SoltanSalman.home_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeHeader {
    private Title title;
    private Description desc;
    private String link;
    private List<HomeImages> imgUrls;
}
