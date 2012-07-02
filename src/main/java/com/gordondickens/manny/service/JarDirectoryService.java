package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.JarDirectory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public interface JarDirectoryService {

    public abstract long countAllJarDirectorys();

    public abstract JarDirectory findJarDirectory(Long id);

    public abstract List<Bundle> findBundles(Long jarDirectoryid);

    public abstract List<JarDirectory> findAllJarDirectorys();

    public abstract List<JarDirectory> findJarDirectoryEntries(int firstResult, int maxResults);

    @Transactional(readOnly = false)
    public abstract void deleteJarDirectory(JarDirectory jarDirectory);

    @Transactional(readOnly = false)
    public abstract void saveJarDirectory(JarDirectory jarDirectory);

    @Transactional(readOnly = false)
    public abstract void scan(JarDirectory fileOrDirectory);

    @Transactional(readOnly = false)
    public abstract JarDirectory updateJarDirectory(JarDirectory jarDirectory);
}
