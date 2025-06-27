package com.Ahmed.SoltanSalman.comman_helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Description {
    @NotBlank(message = "Arabic description must not be blank")
    @NotNull @NotEmpty
    private String ar;
    @NotBlank(message = "English description must not be blank")
    @NotNull @NotEmpty
    private String en;
}
