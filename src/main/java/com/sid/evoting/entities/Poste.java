package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Poste implements Serializable {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPoste;
    private String nomPoste;
    public Long getIdPoste() {
	return idPoste;
  }
    public void setIdPoste(Long idPoste) {
	this.idPoste = idPoste;
   }
    public String getNomPoste() {
	return nomPoste;
  }
    public void setNomPoste(String nomPoste) {
	this.nomPoste = nomPoste;
   }
    public Poste(String nomPoste) {
	super();
	this.nomPoste = nomPoste;
}
public Poste() {
	super();
	// TODO Auto-generated constructor stub
}

  
}
