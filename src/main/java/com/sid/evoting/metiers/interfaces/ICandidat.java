package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Candidat;

import java.util.List;

public interface ICandidat {
	public Candidat saveCandidat(Candidat candidat);
	public Candidat getCandidat(Long idCat);
	public List<Candidat> listCandidat();
	public void updateCandidat(Long idCa, Candidat c);
	public void deleteCandidat(Long idCat);
    public List<Candidat> listCandidatsDep();
    public List<Candidat> listCandidatsDepid(Long idep);
    public Integer nombreCandidatInscrit();

}
