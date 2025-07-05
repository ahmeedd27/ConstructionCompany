package com.Ahmed.SoltanSalman.global_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalUpdateRequest {
    private String imageBase64;
    private Title address;
    private String phone;
    private String email;
    private String workingHours;
}
