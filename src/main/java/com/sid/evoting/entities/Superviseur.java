package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("AD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Superviseur extends User implements Serializable {
	@OneToMany(mappedBy="superviseur")
	private Collection<Election> election;
	
    @JsonIgnore
	public Collection<Election> getElection() {
		return election;
	}

	public void setElection(Collection<Election> election) {
		this.election = election;
	}



	public Superviseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Superviseur(String username, String name, boolean active, String password) {
		super(username, name, active, password);
		// TODO Auto-generated constructor stub
	}

	public Superviseur(String username, String name, boolean active, String password, String service) {
		super(username, name, active, password);
	}
	
	

}
