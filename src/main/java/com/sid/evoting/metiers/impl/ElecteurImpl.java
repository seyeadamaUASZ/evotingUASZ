package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.ElecteurRepository;
import com.sid.evoting.entities.Electeur;
import com.sid.evoting.metiers.interfaces.IElecteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElecteurImpl implements IElecteur {
	@Autowired
	private ElecteurRepository elect;

	@Override
	public Electeur saveElecteur(Electeur electeur) {
		// TODO Auto-generated method stub
		return elect.save(electeur);
	}

	@Override
	public List<Electeur> listElecteur() {
		return elect.findAll();
	}

	@Override
	public Electeur getElecteur(Long id) {
		// TODO Auto-generated method stub
		return elect.getOne(id);
	}

	@Override
	public void modifierElecteur(Long id, Electeur electeur) {
		// TODO Auto-generated method stub
		electeur.setIdUser(id);
		elect.saveAndFlush(electeur);
		
	}

	@Override
	public List<Electeur> listElecteurVote() {
		return elect.listElecteurVote();
	}

	@Override
	public List<Electeur> listElecteurVoteNon() {
		return elect.listElecteurVoteNon();
	}

	@Override
	public Integer nombreElecteurVoter() {
		return elect.nombreelecteurV();
	}

	@Override
	public Integer nombreElecteurVoterN() {
		return elect.nombreElecteurNonV();
	}

	@Override
	public List<Electeur> listElecteurDep(Long idDep) {
		return elect.listElecteurDep(idDep);
	}

	@Override
	public List<Electeur> listElecteurDeps() {
		return elect.electeursDep();
	}

	@Override
	public Integer nombreTotalElecteurs() {
		return elect.nombreTotalElecteur();
	}

	@Override
	public Integer evolutionV() {
		return (nombreElecteurVoter()/nombreTotalElecteurs())*100;
	}

	@Override
	public Integer evolutionNV() {
		return (nombreElecteurVoterN()/nombreTotalElecteurs())*100;
	}

}
