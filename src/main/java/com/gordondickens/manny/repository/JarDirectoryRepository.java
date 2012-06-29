package com.gordondickens.manny.repository;

import com.gordondickens.manny.domain.JarDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JarDirectoryRepository extends JpaRepository<JarDirectory, Long>, JpaSpecificationExecutor<JarDirectory> {
}
