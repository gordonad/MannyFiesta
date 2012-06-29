package com.gordondickens.manny.service.internal;


import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.repository.JarDirectoryRepository;
import com.gordondickens.manny.service.JarDirectoryService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.virgo.util.osgi.manifest.ExportPackage;
import org.eclipse.virgo.util.osgi.manifest.ExportedPackage;
import org.eclipse.virgo.util.osgi.manifest.ImportPackage;
import org.eclipse.virgo.util.osgi.manifest.ImportedPackage;
import org.eclipse.virgo.util.osgi.manifest.internal.StandardBundleManifest;
import org.eclipse.virgo.util.osgi.manifest.parse.ParserLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

@Transactional
@Service
public class JarDirectoryServiceImpl implements JarDirectoryService {

    @Autowired
    JarDirectoryRepository jarDirectoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(JarDirectoryServiceImpl.class);

    @Override
    public void scan(String fileOrDirectory) {
        if (fileOrDirectory == null || StringUtils.isBlank(fileOrDirectory)) return;

        File inFile = new File(fileOrDirectory);
        boolean isDirectory = inFile.isDirectory();
        boolean isFile = inFile.isFile();
        if (!isDirectory && !isFile) return;

        if (isFile) {
            processFile(inFile);
        }

        if (isDirectory) {
            int jarCount = 0;
            logger.debug("Manny is scanning for jar files in {} ...", inFile);
            List<Map<String, Map<String, String>>> results = new ArrayList<Map<String, Map<String, String>>>();

            Collection<File> files = FileUtils.listFiles(inFile, new String[]{"jar"}, true);
            for (File file : files) {
                if (file.isFile()) {
                    jarCount++;
                    processFile(file);
                }
            }
        }
    }

    private void processFile(File jarFileName) {
        try {
            String filePath = jarFileName.getCanonicalPath();
            logger.debug("***** {} *****", filePath.substring(filePath.lastIndexOf('/') + 1));
            JarFile jarFile = new JarFile(jarFileName);
            if (jarFile.getManifest().getMainAttributes().entrySet() != null) {
                Manifest manifest = jarFile.getManifest();
                logger.debug("\tManifest: {}", manifest);
                Map<String, String> jarEntries = new HashMap<String, String>();
                Attributes attributes = manifest.getMainAttributes();
                for (Object key : attributes.keySet()) {
                    String myKey = key.toString();
                    logger.debug("Key '{}'", myKey);
                    if (key instanceof Attributes.Name) {
                        String myVal = attributes.getValue(myKey);
                        jarEntries.put(myKey, myVal);
                    }
                }
                buildManifest(jarEntries);
            } else {
                logger.debug("********** NO Manifest in {} - NOT OSGi Compatible! **********\n", jarFileName.getName());
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    private void buildManifest(Map<String, String> manifest) {
        StandardBundleManifest mani = new StandardBundleManifest(new PrintingParserLogger(), manifest);
        ImportPackage importPackage = mani.getImportPackage();
        List<ImportedPackage> pkgList = importPackage.getImportedPackages();
        for (ImportedPackage pkg : pkgList) {
            logger.debug("<-- IMPORTED PKG {} - V: {}", pkg.getPackageName(), pkg.getVersion().toString());
        }
        ExportPackage exportPackage = mani.getExportPackage();
        List<ExportedPackage> epkgList = exportPackage.getExportedPackages();
        for (ExportedPackage pkg : epkgList) {
            logger.debug("--> EXPORTED PKG {} - V: {}", pkg.getPackageName(), pkg.getVersion().toString());
        }
    }

    private class PrintingParserLogger implements ParserLogger {
        @Override
        public String[] errorReports() {
            return null;
        }

        @Override
        public void outputErrorMsg(Exception re, String item) {
            logger.error("Error: {}", item, re);
        }
    }

    @Override
    public long countAllJarDirectorys() {
        return jarDirectoryRepository.count();
    }

    @Override
    public void deleteJarDirectory(JarDirectory jarDirectory) {
        jarDirectoryRepository.delete(jarDirectory);
    }

    @Override
    public JarDirectory findJarDirectory(Long id) {
        return jarDirectoryRepository.findOne(id);
    }

    @Override
    public List<JarDirectory> findAllJarDirectorys() {
        return jarDirectoryRepository.findAll();
    }

    @Override
    public List<JarDirectory> findJarDirectoryEntries(int firstResult, int maxResults) {
        return jarDirectoryRepository.findAll(new PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveJarDirectory(JarDirectory jarDirectory) {
        jarDirectoryRepository.save(jarDirectory);
    }

    @Override
    public JarDirectory updateJarDirectory(JarDirectory jarDirectory) {
        return jarDirectoryRepository.save(jarDirectory);
    }
}
