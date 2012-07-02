package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.ManifestDetail;
import com.gordondickens.manny.domain.Pkg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public interface BundleService {

    public abstract long countAllBundles();

    public abstract List<Pkg> findImportedPackages(Long bundleId);

    public abstract List<Pkg> findExportedPackages(Long bundleId);

    public abstract List<ManifestDetail> findManifestDetails(Long bundleId);

    public abstract Bundle findBundle(Long id);

    public abstract List<Bundle> findAllBundles();

    public abstract List<Bundle> findBundleEntries(int firstResult, int maxResults);

    @Transactional(readOnly = false)
    public abstract void deleteBundle(Bundle bundle);

    @Transactional(readOnly = false)
    public abstract void saveBundle(Bundle bundle);

    @Transactional(readOnly = false)
    public abstract Bundle updateBundle(Bundle bundle);
}
