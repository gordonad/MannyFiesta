package com.gordondickens.manny.repository;

import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query("select p.jarDirectories from Project p where p.id = :projectId")
    List<JarDirectory> findJarDirectories(@Param("projectId") Long projectId);

}
