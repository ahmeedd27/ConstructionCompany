package com.Ahmed.SoltanSalman.global_functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalUpdateRequest {
    private String imageBase64;
    private SocialMedia socialMedia;
    private String address;
    private String phone;
    private String email;
    private String workingHours;
}
