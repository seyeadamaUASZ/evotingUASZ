package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ufr")
public class UFR {
    @Id
    private String nomufr;
    @OneToMany(mappedBy = "ufr",fetch = FetchType.LAZY)
    private Collection<Departement> departements=new ArrayList<>();
    @JsonIgnore
    public Collection<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Collection<Departement> departements) {
        this.departements = departements;
    }

    public UFR(String nomufr) {
        this.nomufr = nomufr;
    }
    public UFR(){
        super();
    }

    public String getNomufr() {
        return nomufr;
    }

    public void setNomufr(String nomufr) {
        this.nomufr = nomufr;
    }
}
