package com.Ahmed.SoltanSalman.about_page_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.project_functionality.JobTitle;
import com.Ahmed.SoltanSalman.project_functionality.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainMembers {
    private Name name;
    private JobTitle jobTitle;
    private Description desc;
    private String gmail;
}
