package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Poste;

import java.util.List;

public interface IPoste {
	public Poste savePoste(Poste poste);
	public Poste getPoste(Long idPoste);
	public List<Poste> listPoste();
	public void modifierPoste(Long idp, Poste poste);
	public void deletePoste(Long idposte);

}
