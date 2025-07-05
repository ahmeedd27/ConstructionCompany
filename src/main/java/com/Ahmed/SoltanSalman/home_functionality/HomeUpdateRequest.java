package com.Ahmed.SoltanSalman.home_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeUpdateRequest {
    private Title headerTitle;
    private Description headerDescription;
    private String linkVid;
    private List<HomeImages> headerImageBase64;
    private Title aboutUsTitle;
    private Description aboutUsDescription;
    private String aboutUsImageBase64;
    private Title aboutAbhaTitle;
    private Description aboutAbhaDescription;
    private String aboutAbhaImageBase64;
}
