package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import com.Ahmed.SoltanSalman.service.ProjectPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Project Page", description = "Endpoints for project page data")
public class ProjectPageController {
    private final ProjectPageService pageService;

    @GetMapping("/project-page")
    @Operation(summary = "Retrieve project page data", description = "Fetches data for the project page")
    public ProjectPage getProjectPage() {
        return pageService.getData();
    }
}