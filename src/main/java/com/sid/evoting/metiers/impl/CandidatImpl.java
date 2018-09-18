package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.CandidatRepository;
import com.sid.evoting.entities.Candidat;
import com.sid.evoting.metiers.interfaces.ICandidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatImpl implements ICandidat {
	
	@Autowired
	private CandidatRepository candidatRepo;

	@Override
	public Candidat saveCandidat(Candidat candidat) {
		// TODO Auto-generated method stub
		return candidatRepo.save(candidat);
	}

	@Override
	public Candidat getCandidat(Long id) {
		// TODO Auto-generated method stub
		return candidatRepo.getOne(id);
	}

	@Override
	public List<Candidat> listCandidat() {
		// TODO Auto-generated method stub
		return candidatRepo.findAll();
	}

	@Override
	public void updateCandidat(Long idCan, Candidat c) {
		c.setIdUser(idCan);
		candidatRepo.saveAndFlush(c);
		
	}

	@Override
	public void deleteCandidat(Long id) {
		candidatRepo.deleteById(id);
		
	}

	@Override
	public List<Candidat> listCandidatsDep() {
		return candidatRepo.listCandidatDep();
	}

	@Override
	public List<Candidat> listCandidatsDepid(Long idep) {
		return candidatRepo.CandidatsDep(idep);
	}

	@Override
	public Integer nombreCandidatInscrit() {
		return candidatRepo.nombreCandidats();
	}


}
