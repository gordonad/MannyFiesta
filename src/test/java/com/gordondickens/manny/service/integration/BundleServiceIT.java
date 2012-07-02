package com.gordondickens.manny.service.integration;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.domain.Pkg;
import com.gordondickens.manny.service.BundleService;
import com.gordondickens.manny.service.JarDirectoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * User: gordon
 * Date: 7/2/12
 * Time: 5:32 PM
 */
@Transactional
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BundleServiceIT {
    private static final Logger logger = LoggerFactory.getLogger(BundleServiceIT.class);

    @Autowired
    BundleService bundleService;

    @Autowired
    JarDirectoryService jarDirectoryService;

    @Before
    public void directoryFileScan() {
        JarDirectory jarDirectory = new JarDirectory();
        jarDirectory.setName("/opt/virgo-jetty-server-3.5.0.M04/repository/ext");
        jarDirectoryService.saveJarDirectory(jarDirectory);

        logger.debug("\n\n******* SAVED '{}' *******\n\n", jarDirectory.toString());

        assertNotNull(jarDirectory.getId());
        assertNotNull(jarDirectory.getBundles());
        assertTrue(jarDirectory.getBundles().size() > 0);
    }

    @Test
    public void findExportedPackages() {
        Bundle bundle = bundleService.findBundle(1L);
        assertNotNull("One Bundle MUST Exist", bundle);

        List<Pkg> exportedPackages = bundleService.findExportedPackages(1L);
        assertNotNull("Exported Packages MUST exist", exportedPackages);
        assertTrue("At least 1 exported package must exist", exportedPackages.size() > 0);
    }

    @Test
    public void findImportedPackages() {
        Bundle bundle = bundleService.findBundle(1L);
        assertNotNull("One Bundle MUST Exist", bundle);

        List<Pkg> importedPackages = bundleService.findImportedPackages(1L);
        assertNotNull("Exported Packages MUST exist", importedPackages);
        assertTrue("At least 1 exported package must exist", importedPackages.size() > 0);
    }


}
