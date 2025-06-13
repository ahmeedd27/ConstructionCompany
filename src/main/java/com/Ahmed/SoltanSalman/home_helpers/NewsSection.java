package com.Ahmed.SoltanSalman.home_helpers;

import com.Ahmed.SoltanSalman.news_page_helpers.NewP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsSection {
    private String title;
    private String desc;
    private List<NewP> news;
}
