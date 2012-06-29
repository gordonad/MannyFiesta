package com.gordondickens.manny.service;

import com.gordondickens.manny.service.internal.JarDirectoryServiceImpl;
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
        //TODO Add project specific path to make this a system independent unit test
        JarDirectoryService jarDirectoryService = new JarDirectoryServiceImpl();
        jarDirectoryService.scan("/opt/virgo-jetty-server-3.5.0.M04/repository/ext");
//        jarDirectoryService.scan();
    }

    @Test
    public void directoryFileScan() {

    }

    @Test
    public void nonExistentFileOrDirectoryScan() {

    }

    @Test
    public void noJarsFoundScan() {

    }

    @Test
    public void jarWithoutManifestScan() {

    }
}
