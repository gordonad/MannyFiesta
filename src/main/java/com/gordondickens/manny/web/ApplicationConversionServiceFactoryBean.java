package com.gordondickens.manny.web;

import com.gordondickens.manny.domain.Bundle;
import com.gordondickens.manny.domain.JarDirectory;
import com.gordondickens.manny.domain.ManifestDetail;
import com.gordondickens.manny.domain.Pkg;
import com.gordondickens.manny.service.BundleService;
import com.gordondickens.manny.service.JarDirectoryService;
import com.gordondickens.manny.service.ManifestDetailService;
import com.gordondickens.manny.service.PkgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * A central place to register application converters and formatters.
 */
@Configurable
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    @Autowired
    PkgService pkgService;
    @Autowired
    ManifestDetailService manifestDetailService;
    @Autowired
    JarDirectoryService jarDirectoryService;
    @Autowired
    BundleService bundleService;

    @Override
    protected void installFormatters(FormatterRegistry registry) {
        super.installFormatters(registry);
        // Register application converters and formatters
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }

    public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getBundleToStringConverter());
        registry.addConverter(getIdToBundleConverter());
        registry.addConverter(getStringToBundleConverter());
        registry.addConverter(getJarDirectoryToStringConverter());
        registry.addConverter(getIdToJarDirectoryConverter());
        registry.addConverter(getStringToJarDirectoryConverter());
        registry.addConverter(getManifestDetailToStringConverter());
        registry.addConverter(getIdToManifestDetailConverter());
        registry.addConverter(getStringToManifestDetailConverter());
        registry.addConverter(getPkgToStringConverter());
        registry.addConverter(getIdToPkgConverter());
        registry.addConverter(getStringToPkgConverter());
    }

    public Converter<String, Pkg> getStringToPkgConverter() {
        return new Converter<String, Pkg>() {
            public Pkg convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Pkg.class);
            }
        };
    }

    public Converter<Long, Pkg> getIdToPkgConverter() {
        return new Converter<Long, Pkg>() {
            public Pkg convert(Long id) {
                return pkgService.findPkg(id);
            }
        };
    }

    public Converter<Pkg, String> getPkgToStringConverter() {
        return new Converter<Pkg, String>() {
            public String convert(Pkg pkg) {
                return pkg.getName() + ' ' + pkg.getMinVersion() + ' ' + pkg.getMaxVersion();
            }
        };
    }

    public Converter<String, ManifestDetail> getStringToManifestDetailConverter() {
        return new Converter<String, ManifestDetail>() {
            public ManifestDetail convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ManifestDetail.class);
            }
        };
    }

    public Converter<Long, ManifestDetail> getIdToManifestDetailConverter() {
        return new Converter<Long, ManifestDetail>() {
            public ManifestDetail convert(Long id) {
                return manifestDetailService.findManifestDetail(id);
            }
        };
    }

    public Converter<ManifestDetail, String> getManifestDetailToStringConverter() {
        return new Converter<ManifestDetail, String>() {
            public String convert(ManifestDetail manifestDetail) {
                return manifestDetail.getName() + ' ' + manifestDetail.getContents();
            }
        };
    }

    public Converter<String, JarDirectory> getStringToJarDirectoryConverter() {
        return new Converter<String, JarDirectory>() {
            public JarDirectory convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JarDirectory.class);
            }
        };
    }

    public Converter<Long, JarDirectory> getIdToJarDirectoryConverter() {
        return new Converter<Long, JarDirectory>() {
            public JarDirectory convert(Long id) {
                return jarDirectoryService.findJarDirectory(id);
            }
        };
    }

    public Converter<JarDirectory, String> getJarDirectoryToStringConverter() {
        return new Converter<JarDirectory, String>() {
            public String convert(JarDirectory jarDirectory) {
                return jarDirectory.getName();
            }
        };
    }

    public Converter<String, Bundle> getStringToBundleConverter() {
        return new Converter<String, Bundle>() {
            public Bundle convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Bundle.class);
            }
        };
    }

    public Converter<Long, Bundle> getIdToBundleConverter() {
        return new Converter<Long, Bundle>() {
            public Bundle convert(Long id) {
                return bundleService.findBundle(id);
            }
        };
    }

    public Converter<Bundle, String> getBundleToStringConverter() {
        return new Converter<Bundle, String>() {
            public String convert(Bundle bundle) {
                return bundle.getName();
            }
        };
    }
}
