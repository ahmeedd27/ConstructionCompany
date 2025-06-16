package com.Ahmed.SoltanSalman.controller;

import com.Ahmed.SoltanSalman.global_helpers.Header;
import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import com.Ahmed.SoltanSalman.projects_helpers.Project;
import com.Ahmed.SoltanSalman.request.ProjectRequest;
import com.Ahmed.SoltanSalman.service.ProjectPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project-page")
@Tag(name = "Project Page", description = "Endpoints for managing project page")
public class ProjectPageController {

    private final ProjectPageService service;

    @GetMapping()
    @Operation(summary = "Project Page", description = "Fetches the Project Page")
    public ResponseEntity<ProjectPage> getAllProjects(){
        return ResponseEntity.ok(service.getProjectPage());
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update ProjectPage", description = "Update ProjectPage")
    public ResponseEntity<ProjectPage> updateProject(
            @RequestPart(value = "project-page", required = false) Header headerRequest ,
            @RequestPart(value = "file" , required = false) MultipartFile file
    ){
        return ResponseEntity.ok(service.updateProjectPageHeader(headerRequest , file));
    }

}
