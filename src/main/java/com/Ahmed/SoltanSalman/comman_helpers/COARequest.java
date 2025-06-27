package com.Ahmed.SoltanSalman.comman_helpers;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class COARequest {
    @NotBlank(message = "Arabic title must not be blank")
    private String ar;

    @NotBlank(message = "English title must not be blank")
    private String en;
}
