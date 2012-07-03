package com.gordondickens.manny.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @NotNull
    @Column(length = 255)
    private String name;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<JarDirectory> jarDirectories = new HashSet<JarDirectory>();


    public void setJarDirectories(Set<JarDirectory> jarDirectories) {
        this.jarDirectories = jarDirectories;
    }

    public void addJarDirectory(JarDirectory jarDirectory) {
        if (this.jarDirectories == null) {
            this.jarDirectories = new HashSet<JarDirectory>();
        }
        this.jarDirectories.add(jarDirectory);
    }

    public Set<JarDirectory> getJarDirectories() {
        return this.jarDirectories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
