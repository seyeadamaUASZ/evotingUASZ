package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfiliere;
    private String nomFiliere;
    @ManyToOne
    @JoinColumn(name = "id_dep")
    private Departement departement;
    @OneToMany(mappedBy = "filiere",fetch = FetchType.LAZY)
    private Collection<Niveau> niveaux=new ArrayList<>();
    @JsonIgnore
    public Collection<Niveau> getNiveaux() {
        return niveaux;
    }
    @OneToMany(mappedBy = "filiere",fetch = FetchType.LAZY)
    private Collection<User> users;

    @JsonIgnore
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setNiveaux(Collection<Niveau> niveaux) {
        this.niveaux = niveaux;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Filiere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public Long getIdfiliere() {
        return idfiliere;
    }

    public void setIdfiliere(Long idfiliere) {
        this.idfiliere = idfiliere;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public Filiere() {
        super();
    }
}
