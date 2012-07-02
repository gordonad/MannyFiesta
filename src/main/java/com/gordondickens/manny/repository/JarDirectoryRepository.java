package com.gordondickens.manny.repository;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.JarDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JarDirectoryRepository extends JpaRepository<JarDirectory, Long>, JpaSpecificationExecutor<JarDirectory> {

    @Query("select j.bundles from JarDirectory j where j.id = :jarDirectoryId")
    List<Bundle> findBundles(@Param("jarDirectoryId") Long jarDirectoryId);
}
