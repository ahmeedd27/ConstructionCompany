package com.Ahmed.SoltanSalman.contact_us_page_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Contact Us")
public class Contact {
    @Id
    private String _id;
    private Header header;
    private String receiverEmail;
}
