package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.Pkg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public interface PkgService {
    public abstract long countAllPkgs();

    public abstract Pkg findPkg(Long id);

    public abstract List<Pkg> findAllPkgs();

    public abstract List<Pkg> findPkgEntries(int firstResult, int maxResults);

    @Transactional(readOnly = false)
    public abstract void deletePkg(Pkg pkg);

    @Transactional(readOnly = false)
    public abstract void savePkg(Pkg pkg);

    @Transactional(readOnly = false)
    public abstract Pkg updatePkg(Pkg pkg);
}
