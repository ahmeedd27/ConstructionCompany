package com.Ahmed.SoltanSalman.home_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.Ahmed.SoltanSalman.news_functionality.NewDto;
import com.Ahmed.SoltanSalman.project_functionality.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeDto {
    private HomeHeader header;
    private Header aboutUs;
    private List<ProjectDTO> projects;
    private List<NewDto> newsDtoList;
    private Header aboutAbha;
}
