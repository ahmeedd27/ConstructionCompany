package com.Ahmed.SoltanSalman.contact_with_us_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ContactPost")
public class Contact {
    private String name;
    private String email;
    private String subject;
    private String message;
}
