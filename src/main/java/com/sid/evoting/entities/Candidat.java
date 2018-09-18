package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@DiscriminatorValue("CA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidat extends User implements Serializable{
	private byte[] photo;
	private String nomPhoto;
	private String type_candidat;
	private Integer voix;

	public Integer getVoix() {
		return voix;
	}

	public void setVoix(Integer voix) {
		this.voix = voix;
	}

	@ManyToOne
	@JoinColumn(name="id_election")
	private Election election;

    public Candidat(String username, String name, boolean active, String password, byte[] photo, String nomPhoto) {
        super(username, name, active, password);
        this.photo = photo;
        this.nomPhoto = nomPhoto;
    }
    @JsonIgnore
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @JsonIgnore
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	public Candidat(String username, String name, boolean active, String password, String nomPhoto) {
		super(username, name, active, password);
		this.nomPhoto = nomPhoto;
	}

    public String getType_candidat() {
        return type_candidat;
    }

    public void setType_candidat(String type_candidat) {
        this.type_candidat = type_candidat;
    }

    public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidat(String username, String name, boolean active, String password) {
		super(username, name, active, password);
		// TODO Auto-generated constructor stub
	}
	

}
