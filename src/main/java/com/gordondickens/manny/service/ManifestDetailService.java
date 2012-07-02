package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.ManifestDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public interface ManifestDetailService {

    public abstract long countAllManifestDetails();

    public abstract ManifestDetail findManifestDetail(Long id);

    public abstract List<ManifestDetail> findAllManifestDetails();

    public abstract List<ManifestDetail> findManifestDetailEntries(int firstResult, int maxResults);

    @Transactional(readOnly = false)
    public abstract void deleteManifestDetail(ManifestDetail manifestDetail);

    @Transactional(readOnly = false)
    public abstract void saveManifestDetail(ManifestDetail manifestDetail);

    @Transactional(readOnly = false)
    public abstract ManifestDetail updateManifestDetail(ManifestDetail manifestDetail);
}
