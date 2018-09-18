package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Election;

import java.util.List;

public interface IElection {
	public Election saveElection(Election election);
	public Election getElection(Long idElection);
	public List<Election> listElection();
	public void modifierElection(Long ide, Election e);
	public void deleteElection(Long ide);
	public Election getElectionSuper(Long id);
	public Election getElectionOuvert();

}
