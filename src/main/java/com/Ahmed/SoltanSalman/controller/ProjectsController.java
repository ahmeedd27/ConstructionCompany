package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.projects_helpers.Project;
import com.Ahmed.SoltanSalman.service.ProjectsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Tag(name = "Projects", description = "Endpoints for managing projects")
public class ProjectsController {
    private final ProjectsService service;

    @GetMapping()
    @Operation(summary = "Retrieve all projects", description = "Fetches a list of all available projects")
    public List<Project> projects() {
        return service.findAll();
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Retrieve project by slug", description = "Fetches a specific project by its slug")
    public Project getProjectBySlug(@PathVariable String slug) {
        return service.getBySlug(slug);
    }

    @PostMapping()
    @Operation(summary = "Add a new project", description = "Creates a new project with the provided details")
    public String addProject(@RequestBody Project project) {
        return service.addProject(project);
    }
}