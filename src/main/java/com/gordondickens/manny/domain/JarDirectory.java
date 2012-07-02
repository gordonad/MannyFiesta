package com.gordondickens.manny.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class JarDirectory {

    @NotNull
    @Column(length = 255)
    private String name;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Bundle> bundles = new HashSet<Bundle>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Bundle> getBundles() {
        return this.bundles;
    }

    public void setBundles(Set<Bundle> Bundles) {
        this.bundles = Bundles;
    }

    public void addBundle(Bundle bundle) {
        if (this.getBundles() == null) {
            this.bundles = new HashSet<Bundle>();
        }
        this.bundles.add(bundle);
    }


    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
