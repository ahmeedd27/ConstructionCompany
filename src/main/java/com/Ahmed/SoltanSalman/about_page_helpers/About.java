package com.Ahmed.SoltanSalman.about_page_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "About")
public class About {
    private String _id;
    private String slug;
    private Header header;
    private OurStory ourStory;
    private OurVision ourVision;
    private OurResponsibilities ourResponsibilities;
    private OurValues ourValues;
    private ValueResponsibility valueResponsibility;
    private Team team;
    private List<Department> departments;
    private List<Info> info;
    private OurJourney ourJourney;
    private AboutFuture aboutFuture;
    private StrategicPartners strategicPartners;
    private List<MainPartner> mainPartners;
}
