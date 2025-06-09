package com.Ahmed.SoltanSalman.projectpage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectPageController {
    private final ProjectPageService pageService;

    @GetMapping("/projectPage")
    public ProjectPage getProjectPage(){
        return pageService.getData();
    }
}
