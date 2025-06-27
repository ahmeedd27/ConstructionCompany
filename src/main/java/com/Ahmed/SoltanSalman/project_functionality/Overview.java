package com.Ahmed.SoltanSalman.project_functionality;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Overview {
    @NotBlank(message = "Arabic title must not be blank")
    private String ar;

    @NotBlank(message = "English title must not be blank")
    private String en;
}
