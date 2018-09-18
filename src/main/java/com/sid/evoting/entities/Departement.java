package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDep;
    @NotNull
    @Column(unique = true)
    private String nomDep;
    private String description;
    @OneToMany(mappedBy = "departement",fetch = FetchType.LAZY)
    private Collection<User> users=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "ufr")
    private UFR ufr;
    @OneToMany(mappedBy = "departement",fetch = FetchType.LAZY)
    private Collection<Filiere> filieres=new ArrayList<>();
    @JsonIgnore
    public Collection<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(Collection<Filiere> filieres) {
        this.filieres = filieres;
    }

    public UFR getUfr() {
        return ufr;
    }

    public void setUfr(UFR ufr) {
        this.ufr = ufr;
    }
    @JsonIgnore
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Departement(String nomDep, String description) {
        this.nomDep = nomDep;
        this.description = description;
    }

    public Long getIdDep() {
        return idDep;
    }

    public void setIdDep(Long idDep) {
        this.idDep = idDep;
    }

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Departement() {
        super();
    }
}
