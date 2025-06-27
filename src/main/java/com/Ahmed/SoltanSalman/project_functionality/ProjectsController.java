package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.COARequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Tag(name = "Projects", description = "Endpoints for managing projects")
public class ProjectsController {
    private final ProjectsService service;

    @GetMapping("/{slug}")
    @Operation(summary = "Retrieve project by slug", description = "Fetches a specific project by its slug")
    public ResponseEntity<Project> getProjectBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(service.getProjectBySlug(slug));
    }

    @GetMapping()
    @Operation(summary = "Retrieve all projects", description = "Fetches a list of all available projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @DeleteMapping("/{slug}")
    @Operation(summary = "Delete Project By Slug", description = "Delete a Specific Project By Slug")
    public ResponseEntity<String> deleteProject(@PathVariable String slug) {
        return ResponseEntity.ok(service.deleteProjectBySlug(slug));
    }

    @GetMapping("/employees")
    @Operation(summary = "Get Employees Who Worked On The Projects", description = "Get Employees Who Worked On The Projects")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @PostMapping("/employees")
    @Operation(summary = "Add Employee", description = "Add new Employee")
    public ResponseEntity<Employee> addEmployee(
            @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(service.addEmployee(employeeRequest));
    }





    @PostMapping
    @Operation(summary = "Add Project", description = "Add project with base64 images")
    public ResponseEntity<Project> addProjectWithImages(
            @RequestBody @Valid CreateProjectRequest request) {
        return ResponseEntity.ok(service.addProjectWithImages(request));
    }


    @PutMapping("/{slug}")
    @Operation(summary = "Update Project", description = "Update Project")
    public ResponseEntity<Project> updateProject(
            @PathVariable String slug,
            @RequestBody ProjectRequest request
    ) {
        return ResponseEntity.ok(service.updateProject(slug, request));
    }


}