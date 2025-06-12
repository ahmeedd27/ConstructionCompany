package com.Ahmed.SoltanSalman.service;

import com.Ahmed.SoltanSalman.dao.ProjectsRepo;
import com.Ahmed.SoltanSalman.project_page_helpers.ProjectP;
import com.Ahmed.SoltanSalman.project_page_helpers.ProjectPage;
import com.Ahmed.SoltanSalman.dao.ProjectPageRepo;
import com.Ahmed.SoltanSalman.projects_helpers.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {
    private final ProjectsRepo repo;
    private final ProjectPageService pageService;
    private final ProjectPageRepo pageRepo;

    public List<Project> findAll(){
        return repo.findAll();
    }

    public Project getBySlug(String slug) {
        return repo.findBySlug(slug);
    }
    public String addProject(Project project){
        repo.save(project);
        ProjectP p=new ProjectP(
                project.get_id(),
          project.getHeader().getImgUrl(),
                project.getState(),
                project.getHeader().getTitle(),
                project.getHeader().getDesc(),
                project.getSlug(),
                project.getCategory()
        );
        ProjectPage page=pageService.getData();
       page.getProjects().add(p);
      pageRepo.save(page);

      return "Added Successfully";
    }
}
