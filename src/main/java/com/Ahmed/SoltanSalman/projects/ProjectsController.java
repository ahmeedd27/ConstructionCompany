package com.Ahmed.SoltanSalman.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectsController {
    private final ProjectsService service;
    @GetMapping("/projects")
    public List<Project> projects(){
        return service.findAll();
    }
    @GetMapping("/project/{slug}")
    public Project getProjectBySlug(
            @PathVariable String slug
    ){
        return service.getBySlug(slug);
    }
    @PostMapping("/addProject")
    public String addProject(
            @RequestBody Project project
    ){
        return service.addProject(project);

    }
}
