package com.gordondickens.manny.service.internal;


import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.repository.BundleRepository;
import com.gordondickens.manny.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BundleServiceImpl implements BundleService {
    @Autowired
    BundleRepository bundleRepository;

    @Override
    public long countAllBundles() {
        return bundleRepository.count();
    }

    @Override
    public void deleteBundle(Bundle bundle) {
        bundleRepository.delete(bundle);
    }

    @Override
    public Bundle findBundle(Long id) {
        return bundleRepository.findOne(id);
    }

    @Override
    public List<Bundle> findAllBundles() {
        return bundleRepository.findAll();
    }

    @Override
    public List<Bundle> findBundleEntries(int firstResult, int maxResults) {
        return bundleRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveBundle(Bundle bundle) {
        bundleRepository.save(bundle);
    }

    @Override
    public Bundle updateBundle(Bundle bundle) {
        return bundleRepository.save(bundle);
    }
}
