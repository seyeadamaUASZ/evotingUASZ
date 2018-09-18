package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Electeur;

import java.util.List;

public interface IElecteur {
	public Electeur saveElecteur(Electeur electeur);
	public List<Electeur> listElecteur();
	public Electeur getElecteur(Long id);
	public void modifierElecteur(Long id, Electeur electeur);
	public List<Electeur> listElecteurVote();
	public List<Electeur> listElecteurVoteNon();
	public Integer nombreElecteurVoter();
	public Integer nombreElecteurVoterN();
	public List<Electeur> listElecteurDep(Long idDep);
	public List<Electeur> listElecteurDeps();
	public Integer nombreTotalElecteurs();
	public Integer evolutionV();
	public Integer evolutionNV();

}
