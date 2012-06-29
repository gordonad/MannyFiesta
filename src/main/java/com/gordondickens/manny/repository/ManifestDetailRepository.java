package com.gordondickens.manny.repository;

import com.gordondickens.manny.domain.ManifestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifestDetailRepository extends JpaRepository<ManifestDetail, Long>, JpaSpecificationExecutor<ManifestDetail> {
}
