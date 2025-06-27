package com.Ahmed.SoltanSalman.comman_helpers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUpload {
    private int id;
    @NotBlank(message = "Image Base64 must not be blank") @NotEmpty
    @NotNull
    private String imageFile;
    @Valid
    private Title title;
}