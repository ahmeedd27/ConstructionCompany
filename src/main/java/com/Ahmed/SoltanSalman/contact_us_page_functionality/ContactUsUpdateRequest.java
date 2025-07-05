package com.Ahmed.SoltanSalman.contact_us_page_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactUsUpdateRequest {
    private Title title;
    private Description desc;
    private String imageBase64;
    private String receiverEmail;
}
