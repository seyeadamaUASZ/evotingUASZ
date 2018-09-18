package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bulletin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBulletin;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;
    @OneToMany(mappedBy = "bulletin",fetch = FetchType.LAZY)
    private Collection<Electeur> electeurs=new ArrayList<>();

    public Bulletin() {
        super();
    }

    public Long getIdBulletin() {
        return idBulletin;
    }

    public void setIdBulletin(Long idBulletin) {
        this.idBulletin = idBulletin;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
    @JsonIgnore
    public Collection<Electeur> getElecteurs() {
        return electeurs;
    }

    public void setElecteurs(Collection<Electeur> electeurs) {
        this.electeurs = electeurs;
    }
}
