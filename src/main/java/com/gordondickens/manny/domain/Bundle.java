package com.gordondickens.manny.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bundle {

    @Size(min = 1)
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

    public Set<Pkg> getExportPackages() {
        return this.exportPackages;
    }

    public void setImportPackages(Set<Pkg> importPackages) {
        this.importPackages = importPackages;
    }

    public Set<Pkg> getImportPackages() {
        return this.importPackages;
    }

    public void setManifestDetails(Set<ManifestDetail> manifestDetails) {
        this.manifestDetails = manifestDetails;
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
