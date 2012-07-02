package com.gordondickens.manny.repository;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.ManifestDetail;
import com.gordondickens.manny.domain.Pkg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Long>, JpaSpecificationExecutor<Bundle> {

    @Query("select b.exportPackages from Bundle b where b.id = :bundleId")
    List<Pkg> findExportedPackages(@Param("bundleId") Long bundleId);

    @Query("select b.importPackages from Bundle b where b.id = :bundleId")
    List<Pkg> findImportedPackages(@Param("bundleId") Long bundleId);

    @Query("select b.manifestDetails from Bundle b where b.id = :bundleId")
    List<ManifestDetail> findManifestDetails(@Param("bundleId") Long bundleId);
}
