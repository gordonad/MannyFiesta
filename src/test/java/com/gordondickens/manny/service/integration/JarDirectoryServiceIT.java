package com.gordondickens.manny.service.integration;

import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.service.JarDirectoryService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * User: gordon
 * Date: 6/29/12
 * Time: 3:16 PM
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JarDirectoryServiceIT {
    private static final Logger logger = LoggerFactory.getLogger(JarDirectoryServiceIT.class);

    @Autowired
    JarDirectoryService jarDirectoryService;

    @Value("${pagination_records_per_page}")
    Integer maxRecordsPerPage = 10;

    @Test
    public void checkRecordsPerPageSetFromProperties() {
        assertNotNull("Records Per Page MUST exist", maxRecordsPerPage);
        logger.debug("Records per page = '{}'", maxRecordsPerPage);
        assertTrue("Records Per Page Must be greater than 10", maxRecordsPerPage > 10);
    }


    @Test
    public void directoryFileScan() {
        JarDirectory jarDirectory = new JarDirectory();
        jarDirectory.setName("/opt/virgo-jetty-server-3.5.0.M04/repository/ext");
        jarDirectoryService.saveJarDirectory(jarDirectory);

        logger.debug("\n\n******* SAVED '{}' *******\n\n", jarDirectory.toString());

        assertNotNull(jarDirectory.getId());
        assertNotNull(jarDirectory.getBundles());
        assertTrue(jarDirectory.getBundles().size() > 0);
    }

    @Ignore("TODO")
    @Test
    public void jarFileScan() {
        //TODO
    }

    @Ignore("TODO")
    @Test
    public void nonExistentFileOrDirectoryScan() {
        // TODO
    }

    @Ignore("TODO")
    @Test
    public void noJarsFoundScan() {
        //TODO
    }

    @Ignore("TODO")
    @Test
    public void jarWithoutManifestScan() {
        //TODO
    }
}
