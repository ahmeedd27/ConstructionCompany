package com.Ahmed.SoltanSalman.contact_us_Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Contact Us")
public class ContactUs {
    private String title;
    private String desc;
    private String imgUrl;
}
