package com.gordondickens.manny.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: gordon
 * Date: 6/29/12
 * Time: 3:16 PM
 */
public class JarDirectoryServiceTests {
    private static final Logger logger = LoggerFactory.getLogger(JarDirectoryServiceTests.class);

    @Test
    public void jarFileScan() {
        //TODO
    }

    @Test
    public void directoryFileScan() {
        //TODO Add project specific path to make this a system independent unit test
//        JarDirectoryService jarDirectoryService = new JarDirectoryServiceImpl();
//        JarDirectory jarDirectory = new JarDirectory();
//        jarDirectory.setName("/opt/virgo-jetty-server-3.5.0.M04/repository/ext");
//        jarDirectoryService.scan(jarDirectory);
    }

    @Test
    public void nonExistentFileOrDirectoryScan() {
        // TODO
    }

    @Test
    public void noJarsFoundScan() {
        //TODO
    }

    @Test
    public void jarWithoutManifestScan() {
        //TODO
    }
}
