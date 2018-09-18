package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Superviseur;

import java.util.List;

public interface ISuperviseur {
	public List<Superviseur> listSuperviseur();
	public Superviseur getSuperviseur(Long id);
	public Superviseur saveSuperviseur(Superviseur superviseur);
	

}
