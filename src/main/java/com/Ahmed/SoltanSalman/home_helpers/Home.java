package com.Ahmed.SoltanSalman.home_helpers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Home")
public class Home {
    private String _id;
    private String slug;
    private Hero hero;
    private About about;
    private ProjectSection projectsSection;
    private  NewsSection newsSection;
    private Area area;
    private Numbers numbers;
}
