package com.Ahmed.SoltanSalman.project_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.PageUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project-page")
@Tag(name = "Project Page", description = "Endpoints for managing project page")
public class ProjectPageController {

    private final ProjectPageService service;

    @GetMapping()
    @Operation(summary = "Project Page", description = "Fetches the Project Page")
    public ResponseEntity<ProjectPageDTO> getProjectPage() {
        return ResponseEntity.ok(service.getProjectPage());
    }

    @PutMapping
    @Operation(summary = "Update ProjectPage", description = "Update ProjectPage")
    public ResponseEntity<ProjectPage> updateProjectPage(
            @RequestBody PageUpdateRequest request
    ) {
        return ResponseEntity.ok(service.updateProjectPageHeader(request));
    }

}
