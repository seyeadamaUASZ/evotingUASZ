package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Bulletin;
import com.sid.evoting.entities.Candidat;

import java.util.List;

public interface IBulletin {
    public Bulletin saveBulletin(Bulletin bulletin);
    public List<Bulletin> listBulletins();
    public Bulletin getBulletin(Long idBulletin);
    public List<Bulletin> compterVoixForCandidat();
    public List<Candidat> listCandidatsBulltin();
    public List<Candidat> listCandidatsBulletinDep(Long id);
    public List<Candidat> listCandidatBuDepF(Long idf, Long idn);
    public Bulletin bulletinC(Long idc);
    public Integer nombreVoixCandidat(Long id);

}
