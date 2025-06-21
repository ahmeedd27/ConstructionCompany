package com.Ahmed.SoltanSalman.home_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import com.Ahmed.SoltanSalman.news_functionality.NewDto;
import com.Ahmed.SoltanSalman.project_functionality.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Home")
public class Home {
    @Id
    private String _id;
    private HomeHeader header;
    private Header aboutUs;
    private List<ProjectDTO> projects;
    private List<NewDto> newsDtoList;
    private AboutAbha aboutAbha;
    private List<AboutNumbers> abhaNumbers;
}
