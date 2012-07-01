package com.gordondickens.manny.service.internal;


import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.domain.ManifestDetail;
import com.gordondickens.manny.domain.Pkg;
import com.gordondickens.manny.repository.BundleRepository;
import com.gordondickens.manny.repository.JarDirectoryRepository;
import com.gordondickens.manny.repository.ManifestDetailRepository;
import com.gordondickens.manny.repository.PkgRepository;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * JarDirectory 0..n Bundles
 * Bundle 0..n ManifestDetail (essentially a key/value store)
 * Bundle 0..n Pkg for ImportPackages
 * Bundle 0..n Pkg for ExportPackages
 */

@Transactional
@Service
public class JarDirectoryServiceImpl implements JarDirectoryService {

    @Autowired
    JarDirectoryRepository jarDirectoryRepository;

    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    ManifestDetailRepository manifestDetailRepository;

    @Autowired
    PkgRepository pkgRepository;

    private static final Logger logger = LoggerFactory.getLogger(JarDirectoryServiceImpl.class);

    @Override
    public void saveJarDirectory(JarDirectory jarDirectory) {
        scan(jarDirectory);
        jarDirectoryRepository.save(jarDirectory);
    }

    @Override
    public void scan(JarDirectory fileOrDirectory) {
        if (fileOrDirectory == null || fileOrDirectory.getName() == null || StringUtils.isBlank(fileOrDirectory.getName()))
            return;
        int totalJarCount = 0;
        File inFile = new File(fileOrDirectory.getName());
        boolean isDirectory = inFile.isDirectory();
        boolean isFile = inFile.isFile();
        if (!isDirectory && !isFile) return;

        if (isFile) {
            processFile(inFile);
            totalJarCount++;
        }

        if (isDirectory) {
            int jarCount = 0;
            logger.debug("Manny is scanning for jar files in {} ...", inFile);
            Collection<File> files = FileUtils.listFiles(inFile, new String[]{"jar"}, true);
            for (File file : files) {
                if (file.isFile()) {
                    jarCount++;
                    Bundle bundle = processFile(file);
                    if (bundle != null) {
                        fileOrDirectory.addBundle(bundle);
                    }
                }
            }
            totalJarCount += jarCount;
            logger.debug("Processed {} files for '{}'", jarCount, inFile.getAbsolutePath());
        }
        logger.debug("Processed {} files total.", totalJarCount);
    }

    private Bundle processFile(File jarFileName) {
        Bundle bundle = null;
        try {
            String filePath = jarFileName.getCanonicalPath();
            logger.debug("***** {} *****", filePath.substring(filePath.lastIndexOf('/') + 1));
            JarFile jarFile = new JarFile(jarFileName);
            if (jarFile.getManifest().getMainAttributes().entrySet() != null) {
                //TODO Make Bundle Unique by path
                bundle = createBundle(filePath);
                Manifest manifest = jarFile.getManifest();
                logger.debug("\tManifest: {}", manifest);
                Map<String, String> jarEntries = new HashMap<String, String>();
                Attributes attributes = manifest.getMainAttributes();
                for (Object key : attributes.keySet()) {
                    String myKey = key.toString();
                    logger.debug("Key '{}'", myKey);
                    if (key instanceof Attributes.Name) {
                        String myVal = attributes.getValue(myKey);
                        createManifestDetails(bundle, myKey, myVal);
                        jarEntries.put(myKey, myVal);
                    }
                }
                buildManifest(bundle, jarEntries);
            } else {
                logger.debug("********** NO Manifest in {} - NOT OSGi Compatible! **********\n", jarFileName.getName());
                return null;
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return bundle;
    }

    private void buildManifest(Bundle bundle, Map<String, String> manifest) {
        StandardBundleManifest mani = new StandardBundleManifest(new PrintingParserLogger(), manifest);
        ImportPackage importPackage = mani.getImportPackage();
        List<ImportedPackage> pkgList = importPackage.getImportedPackages();
        for (ImportedPackage pkg : pkgList) {
            logger.debug("<-- IMPORTED PKG {} - V: {}", pkg.getPackageName(), pkg.getVersion().toString());
            logger.debug("<-- IMPORTED PKG {} - BV.ParseString: {}", pkg.getPackageName(), pkg.getVersion().toParseString());
            logger.debug("<-- IMPORTED PKG {} - BV.String: {}", pkg.getPackageName(), pkg.getVersion().toString());
            logger.debug("<-- IMPORTED PKG {} - BV.Floor: {}", pkg.getPackageName(), pkg.getVersion().getFloor().toString());


            createImportPackage(bundle, pkg.getPackageName(),
                    pkg.getVersion().getFloor().toString(),
                    (pkg.getVersion().getCeiling() != null) ? pkg.getVersion().getCeiling().toString() : "oo");
        }
        ExportPackage exportPackage = mani.getExportPackage();
        List<ExportedPackage> epkgList = exportPackage.getExportedPackages();
        for (ExportedPackage pkg : epkgList) {
            logger.debug("--> EXPORTED PKG {} - V: {}", pkg.getPackageName(), pkg.getVersion().toString());
            createExportPackage(bundle, pkg.getPackageName(),
                    pkg.getVersion().toString());
        }
    }


    private void createImportPackage(Bundle bundle, String name, String min, String max) {
        Pkg pkg = createPackage(bundle, name, min, max);
        bundle.addImportPackage(pkg);
    }

    private void createExportPackage(Bundle bundle, String name, String version) {
        Pkg pkg = createPackage(bundle, name, version, version);
        bundle.addExportPackage(pkg);
    }

    private Pkg createPackage(Bundle bundle, String name, String min, String max) {
        Pkg pkg = new Pkg();
        pkg.setName(name);
        pkg.setMinVersion(min);
        pkg.setMaxVersion(max);
        return pkg;
    }

    private Bundle createBundle(String name) {
        // TODO Search for Bundle First, replace if already exists
        Bundle bundle = new Bundle();
        bundle.setName(name);
        return bundle;
    }

    private void createManifestDetails(Bundle bundle, String key, String value) {
        ManifestDetail manifestDetail = new ManifestDetail();
        manifestDetail.setName(key);
        manifestDetail.setContents(value);
        bundle.addManifestDetail(manifestDetail);
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
    public JarDirectory updateJarDirectory(JarDirectory jarDirectory) {
        return jarDirectoryRepository.save(jarDirectory);
    }
}
