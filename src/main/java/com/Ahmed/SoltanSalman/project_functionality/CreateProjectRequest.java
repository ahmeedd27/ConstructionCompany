package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.COARequest;
import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.ImageUpload;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest {

    @NotNull(message = "Title must not be null")
    @Valid
    private Title title;
    @NotNull(message = "Description must not be null")
    @Valid
    private Description desc;
    @NotNull
    @Valid
    private Overview overview;
    @NotNull
    @Size(min = 1, message = "objectives list must contain at least one image")
    @Valid
    private List<COARequest> objectives;
    @NotNull
    @Size(min = 1, message = "achievements list must contain at least one image")
    @Valid
    private List<COARequest> achievements;
    @NotNull(message = "Team must not be null")
    private List<Employee> team;
    @NotNull
    private Specification specification;
    @NotNull(message = "Images list must not be null")
    @Size(min = 1, message = "Images list must contain at least one image")
    @Valid
    private List<ImageUpload> images;
    @NotBlank @NotNull @NotEmpty
    private String stateInEnglish;

}
