package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.BulletinRepository;
import com.sid.evoting.entities.Bulletin;
import com.sid.evoting.entities.Candidat;
import com.sid.evoting.metiers.interfaces.IBulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinImpl implements IBulletin {
    @Autowired
    private BulletinRepository bulletinRepo;
    @Override
    public Bulletin saveBulletin(Bulletin bulletin) {
        return bulletinRepo.save(bulletin);
    }

    @Override
    public List<Bulletin> listBulletins() {
        return bulletinRepo.findAll();
    }

    @Override
    public Bulletin getBulletin(Long idBulletin) {
        return bulletinRepo.getOne(idBulletin);
    }

    @Override
    public List<Bulletin> compterVoixForCandidat() {
        return bulletinRepo.compterVoixForCandidat();
    }

    @Override
    public List<Candidat> listCandidatsBulltin() {
        return bulletinRepo.listCandidatBulletin();
    }

    @Override
    public List<Candidat> listCandidatsBulletinDep(Long id) {
        return bulletinRepo.listCandidatsBulletinDep(id);
    }

    @Override
    public List<Candidat> listCandidatBuDepF(Long idf, Long idn) {
        return bulletinRepo.listCandidatDepFN(idf,idn);
    }

    @Override
    public Bulletin bulletinC(Long idc) {
        return bulletinRepo.bulletinC(idc);
    }

    @Override
    public Integer nombreVoixCandidat(Long id) {
        return bulletinRepo.compteVoixCandidat(id);
    }
}
