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
public class AboutUpdateRequest {
    private Title headerTitle;
    private Description headerDesc;
    private String headerImageBase64;
    private Title storyTitle;
    private Description storyDesc;
    private String storyImageBase64;
    private List<MainMembers> members;
}
