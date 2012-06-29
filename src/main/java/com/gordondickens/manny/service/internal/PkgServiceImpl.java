package com.gordondickens.manny.service.internal;


import com.gordondickens.manny.domain.Pkg;
import com.gordondickens.manny.repository.PkgRepository;
import com.gordondickens.manny.service.PkgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PkgServiceImpl implements PkgService {
    @Autowired
    PkgRepository pkgRepository;

    @Override
    public Pkg updatePkg(Pkg pkg) {
        return pkgRepository.save(pkg);
    }

    @Override
    public void savePkg(Pkg pkg) {
        pkgRepository.save(pkg);
    }

    @Override
    public List<Pkg> findPkgEntries(int firstResult, int maxResults) {
        return pkgRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Pkg> findAllPkgs() {
        return pkgRepository.findAll();
    }

    @Override
    public Pkg findPkg(Long id) {
        return pkgRepository.findOne(id);
    }

    @Override
    public void deletePkg(Pkg pkg) {
        pkgRepository.delete(pkg);
    }

    @Override
    public long countAllPkgs() {
        return pkgRepository.count();
    }
}
