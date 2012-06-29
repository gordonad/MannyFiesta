package com.gordondickens.manny.service.internal;


import com.gordondickens.manny.domain.ManifestDetail;
import com.gordondickens.manny.repository.ManifestDetailRepository;
import com.gordondickens.manny.service.ManifestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManifestDetailServiceImpl implements ManifestDetailService {
    @Autowired
    ManifestDetailRepository manifestDetailRepository;

    @Override
    public ManifestDetail updateManifestDetail(ManifestDetail manifestDetail) {
        return manifestDetailRepository.save(manifestDetail);
    }

    @Override
    public void saveManifestDetail(ManifestDetail manifestDetail) {
        manifestDetailRepository.save(manifestDetail);
    }

    @Override
    public List<ManifestDetail> findManifestDetailEntries(int firstResult, int maxResults) {
        return manifestDetailRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<ManifestDetail> findAllManifestDetails() {
        return manifestDetailRepository.findAll();
    }

    @Override
    public ManifestDetail findManifestDetail(Long id) {
        return manifestDetailRepository.findOne(id);
    }

    @Override
    public void deleteManifestDetail(ManifestDetail manifestDetail) {
        manifestDetailRepository.delete(manifestDetail);
    }

    @Override
    public long countAllManifestDetails() {
        return manifestDetailRepository.count();
    }
}
