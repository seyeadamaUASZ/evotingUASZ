package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Niveau implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNiv;
    @Column(name = "nom_niveau")
    private String nomNiveau;
    @OneToMany(mappedBy = "niveau")
    private Collection<User> users;
    @ManyToOne
    @JoinColumn(name = "id_filiere")
    private Filiere filiere;

    public void setIdNiv(Long idNiv) {
        this.idNiv = idNiv;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Niveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }

    public Long getIdNiv() {
        return idNiv;
    }

    public String getNomNiveau() {
        return nomNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }

    public Niveau() {
        super();
    }
}
