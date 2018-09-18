package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.ElectionRepositoy;
import com.sid.evoting.entities.Election;
import com.sid.evoting.metiers.interfaces.IElection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionImpl implements IElection{
	@Autowired
	private ElectionRepositoy electionRepo;

	@Override
	public Election saveElection(Election election) {
		// TODO Auto-generated method stub
		return electionRepo.save(election);
	}

	@Override
	public Election getElection(Long idElection) {
		// TODO Auto-generated method stub
		return electionRepo.getOne(idElection);
	}

	@Override
	public List<Election> listElection() {
		// TODO Auto-generated method stub
		return electionRepo.findAll();
	}

	@Override
	public void modifierElection(Long ide, Election e) {
		// TODO Auto-generated method stub
		e.setIdElection(ide);
		electionRepo.saveAndFlush(e);
		
	}

	@Override
	public void deleteElection(Long ide) {
		// TODO Auto-generated method stub
		electionRepo.deleteById(ide);
	}

	@Override
	public Election getElectionSuper(Long id) {
		return electionRepo.getElectionSuper(id);
	}

	@Override
	public Election getElectionOuvert() {
		return electionRepo.getElectionOuvert();
	}

}
