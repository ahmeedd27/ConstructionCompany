package com.Ahmed.SoltanSalman.about_page_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OurVision {
    private Title title;
    private Description desc;
    private List<OurVisionList> list;
}
