package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.PosteRepository;
import com.sid.evoting.entities.Poste;
import com.sid.evoting.metiers.interfaces.IPoste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteImpl implements IPoste{
	@Autowired
	private PosteRepository posteRepo;

	@Override
	public Poste savePoste(Poste poste) {
		// TODO Auto-generated method stub
		return posteRepo.save(poste);
	}

	@Override
	public Poste getPoste(Long idPoste) {
		// TODO Auto-generated method stub
		return posteRepo.getOne(idPoste);
	}

	@Override
	public List<Poste> listPoste() {
		// TODO Auto-generated method stub
		return posteRepo.findAll();
	}

	@Override
	public void modifierPoste(Long idp, Poste poste) {
		// TODO Auto-generated method stub
		poste.setIdPoste(idp);
		posteRepo.saveAndFlush(poste);
		
	}

	@Override
	public void deletePoste(Long idposte) {
		// TODO Auto-generated method stub
		posteRepo.deleteById(idposte);
		
	}

}
