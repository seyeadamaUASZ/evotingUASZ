package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Election implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long idElection;
  private String nomElection;
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date dateElection;
  private String description;
  private boolean ouvert;

  //pour chaque election, on a les electeurs cibles

    private String electeurCible;

    public String getElecteurCible() {
        return electeurCible;
    }

    public void setElecteurCible(String electeurCible) {
        this.electeurCible = electeurCible;
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    @ManyToOne
  @JoinColumn(name="id_superviseur")
  private Superviseur superviseur;
  @OneToMany(mappedBy="election")
  private Collection<Candidat> candidats;
  @OneToOne
  @JoinColumn(name="id_poste")
  private Poste poste;

    public Election(String nomElection, Date dateElection, String description) {
        this.nomElection = nomElection;
        this.dateElection = dateElection;
        this.description = description;
        this.ouvert = false;
    }

    public Election(String nomElection, Date dateElection, String description, String electeurCible) {
        this.nomElection = nomElection;
        this.dateElection = dateElection;
        this.description = description;
        this.electeurCible = electeurCible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //@JsonIgnore
    public Superviseur getSuperviseur() {
	return superviseur;
}
public void setSuperviseur(Superviseur superviseur) {
	this.superviseur = superviseur;
}
@JsonIgnore
public Collection<Candidat> getCandidats() {
	return candidats;
}
@JsonSetter
public void setCandidats(Collection<Candidat> candidats) {
	this.candidats = candidats;
}
public Poste getPoste() {
	return poste;
}
public void setPoste(Poste poste) {
	this.poste = poste;
}
public Long getIdElection() {
	return idElection;
}
public void setIdElection(Long idElection) {
	this.idElection = idElection;
}
public String getNomElection() {
	return nomElection;
}
public void setNomElection(String nomElection) {
	this.nomElection = nomElection;
}
public Date getDateElection() {
	return dateElection;
}
public void setDateElection(Date dateElection) {
	this.dateElection = dateElection;
}
public Election(String nomElection, Date dateElection) {
	super();
	this.nomElection = nomElection;
	this.dateElection = dateElection;
}
public Election() {
	super();
	// TODO Auto-generated constructor stub
}
  
}
