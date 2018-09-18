package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@DiscriminatorValue("EL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Electeur extends User implements Serializable{
	private String typeElecteur;
	private int vote;
    @ManyToOne
    @JoinColumn(name = "id_bulletin")
	private Bulletin bulletin;
    @JsonIgnore
    public Bulletin getBulletin() {
        return bulletin;
    }
    @JsonSetter
    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getTypeElecteur() {
		return typeElecteur;
	}

	public void setTypeElecteur(String typeElecteur) {
		this.typeElecteur = typeElecteur;
	}

	public Electeur(String username, String name, boolean active, String password, String typeElecteur) {
		super(username, name, active, password);
		this.typeElecteur = typeElecteur;
		this.vote=0;
	}

	public Electeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Electeur(String username, String name, boolean active, String password) {
		super(username, name, active, password);
		// TODO Auto-generated constructor stub
	}
	

}
