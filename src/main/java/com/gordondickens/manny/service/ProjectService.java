package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.domain.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public interface ProjectService {

    public abstract long countAllProjects();

    public abstract List<JarDirectory> findJarDirectories(Long projectId);

    public abstract Project findProject(Long id);

    public abstract List<Project> findAllProjects();

    public abstract List<Project> findProjectEntries(int firstResult, int maxResults);

    @Transactional(readOnly = false)
    public abstract void deleteProject(Project Project);

    @Transactional(readOnly = false)
    public abstract void saveProject(Project Project);

    @Transactional(readOnly = false)
    public abstract Project updateProject(Project Project);
}
