package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategicPartners {
    private String title;
    private String desc;
    private List<StrategicPartner> spartnersList;
}
