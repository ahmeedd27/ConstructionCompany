package com.Ahmed.SoltanSalman.news_functionality;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @NotBlank(message = "Arabic article must not be blank")
    @NotEmpty
    @NotBlank
    private String ar;
    @NotBlank(message = "English article must not be blank")
    @NotEmpty @NotBlank
    private String en;
}
