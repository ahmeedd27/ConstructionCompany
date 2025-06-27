package com.Ahmed.SoltanSalman.about_page_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
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
    private Header header;
    private Header ourStory;
    private OurVision ourVision;
    private List<MainMembers> mainMembers;
    private List<Departments> departments;
    private List<SPartners> sPartners;
    private List<Header> mPartners;
}
