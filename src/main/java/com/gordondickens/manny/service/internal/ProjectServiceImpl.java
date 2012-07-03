package com.gordondickens.manny.service.internal;

import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.domain.Project;
import com.gordondickens.manny.repository.ProjectRepository;
import com.gordondickens.manny.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: gordon
 * Date: 7/2/12
 * Time: 7:57 PM
 */
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public long countAllProjects() {
        return projectRepository.count();
    }

    @Override
    public List<JarDirectory> findJarDirectories(Long projectId) {
        return projectRepository.findJarDirectories(projectId);

    }

    @Override
    public Project findProject(Long id) {
        return projectRepository.findOne(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findProjectEntries(int firstResult, int maxResults) {
        return projectRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }
}
