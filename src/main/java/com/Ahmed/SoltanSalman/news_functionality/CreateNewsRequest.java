package com.Ahmed.SoltanSalman.news_functionality;


import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import com.Ahmed.SoltanSalman.project_functionality.ProjectCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewsRequest {
    @NotNull(message = "Title must not be null")
    @Valid
    private Title title;

    @NotNull(message = "Description must not be null")
    @Valid
    private Description desc;

    @NotBlank(message = "Image Base64 must not be blank")
    private String imageBase64;

    @NotNull(message = "Article must not be null")
    @Valid
    private Article article;

    @NotNull(message = "Category must not be null")
    private NewsCategory category;

    @NotNull(message = "isFeatured must be explicitly set to true or false")
    private boolean isFeatured;
}
