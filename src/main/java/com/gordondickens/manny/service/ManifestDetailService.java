package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.ManifestDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface ManifestDetailService {
    public abstract long countAllManifestDetails();

    public abstract void deleteManifestDetail(ManifestDetail manifestDetail);

    public abstract ManifestDetail findManifestDetail(Long id);

    public abstract List<ManifestDetail> findAllManifestDetails();

    public abstract List<ManifestDetail> findManifestDetailEntries(int firstResult, int maxResults);

    public abstract void saveManifestDetail(ManifestDetail manifestDetail);

    public abstract ManifestDetail updateManifestDetail(ManifestDetail manifestDetail);
}
