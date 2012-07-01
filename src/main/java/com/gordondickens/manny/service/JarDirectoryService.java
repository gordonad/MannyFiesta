package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.JarDirectory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface JarDirectoryService {
    public abstract void scan(JarDirectory fileOrDirectory);

    public abstract long countAllJarDirectorys();

    public abstract void deleteJarDirectory(JarDirectory jarDirectory);

    public abstract JarDirectory findJarDirectory(Long id);

    public abstract List<JarDirectory> findAllJarDirectorys();

    public abstract List<JarDirectory> findJarDirectoryEntries(int firstResult, int maxResults);

    public abstract void saveJarDirectory(JarDirectory jarDirectory);

    public abstract JarDirectory updateJarDirectory(JarDirectory jarDirectory);
}
