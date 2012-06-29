package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.Pkg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface PkgService {
    public abstract long countAllPkgs();

    public abstract void deletePkg(Pkg pkg);

    public abstract Pkg findPkg(Long id);

    public abstract List<Pkg> findAllPkgs();

    public abstract List<Pkg> findPkgEntries(int firstResult, int maxResults);

    public abstract void savePkg(Pkg pkg);

    public abstract Pkg updatePkg(Pkg pkg);

}
