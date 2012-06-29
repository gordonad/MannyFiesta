package com.gordondickens.manny.repository;

import com.gordondickens.manny.domain.Pkg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PkgRepository extends JpaRepository<Pkg, Long>, JpaSpecificationExecutor<Pkg> {
}
