package com.gordondickens.manny.service;

import com.gordondickens.manny.domain.Bundle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface BundleService {

    public abstract long countAllBundles();

    public abstract void deleteBundle(Bundle bundle);

    public abstract Bundle findBundle(Long id);

    public abstract List<Bundle> findAllBundles();

    public abstract List<Bundle> findBundleEntries(int firstResult, int maxResults);

    public abstract void saveBundle(Bundle bundle);

    public abstract Bundle updateBundle(Bundle bundle);

}
