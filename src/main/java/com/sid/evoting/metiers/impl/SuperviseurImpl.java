package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.SuperviseurRepository;
import com.sid.evoting.entities.Superviseur;
import com.sid.evoting.metiers.interfaces.ISuperviseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperviseurImpl implements ISuperviseur {
    @Autowired
	private SuperviseurRepository superRepo;
	@Override
	public List<Superviseur> listSuperviseur() {
		// TODO Auto-generated method stub
		return superRepo.findAll();
	}

	@Override
	public Superviseur getSuperviseur(Long id) {
		// TODO Auto-generated method stub
		return superRepo.getOne(id);
	}

	@Override
	public Superviseur saveSuperviseur(Superviseur superviseur) {
		return superRepo.save(superviseur);
	}

}
