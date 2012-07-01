package com.gordondickens.manny.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bundle {

    @NotNull
    @Column(length = 255)
    private String name;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ManifestDetail> manifestDetails = new HashSet<ManifestDetail>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Pkg> importPackages = new HashSet<Pkg>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Pkg> exportPackages = new HashSet<Pkg>();
    @Version
    @Column(name = "version")
    private Integer version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setExportPackages(Set<Pkg> exportPackages) {
        this.exportPackages = exportPackages;
    }

    public void addExportPackage(Pkg exportPackage) {
        if (this.exportPackages == null) {
            this.exportPackages = new HashSet<Pkg>();
        }
        this.exportPackages.add(exportPackage);
    }

    public Set<Pkg> getExportPackages() {
        return this.exportPackages;
    }

    public void setImportPackages(Set<Pkg> importPackages) {
        this.importPackages = importPackages;
    }

    public void addImportPackage(Pkg importPackage) {
        if (this.importPackages == null) {
            this.importPackages = new HashSet<Pkg>();
        }
        this.importPackages.add(importPackage);
    }

    public Set<Pkg> getImportPackages() {
        return this.importPackages;
    }

    public void setManifestDetails(Set<ManifestDetail> manifestDetails) {
        this.manifestDetails = manifestDetails;
    }

    public void addManifestDetail(ManifestDetail manifestDetail) {
        if (this.manifestDetails == null) {
            this.manifestDetails = new HashSet<ManifestDetail>();
        }
        this.manifestDetails.add(manifestDetail);
    }

    public Set<ManifestDetail> getManifestDetails() {
        return this.manifestDetails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
