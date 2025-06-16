package com.Ahmed.SoltanSalman.request;

import com.Ahmed.SoltanSalman.projects_helpers.JobTitle;
import com.Ahmed.SoltanSalman.projects_helpers.Name;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "can not be null")
    @NotNull(message = "can not be null")
    private Name name;
    @NotBlank(message = "can not be null")
    @NotNull(message = "can not be null")
    private JobTitle jobTitle;
}
