package com.Ahmed.SoltanSalman.projects;

import com.Ahmed.SoltanSalman.projectpage.ProjectP;
import com.Ahmed.SoltanSalman.projectpage.ProjectPage;
import com.Ahmed.SoltanSalman.projectpage.ProjectPageRepo;
import com.Ahmed.SoltanSalman.projectpage.ProjectPageService;
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

        ProjectP p=new ProjectP(
          project.getHeader().getImgUrl(),
                project.getState(),
                project.getHeader().getTitle(),
                project.getHeader().getDesc()
        );
        ProjectPage page=pageService.getData();
       page.getProjects().add(p);
      pageRepo.save(page);
      repo.save(project);
      return "Added Successfully";
    }
}
