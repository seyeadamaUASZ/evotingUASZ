package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.NiveauRepository;
import com.sid.evoting.entities.Niveau;
import com.sid.evoting.metiers.interfaces.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauImpl implements INiveau {
    @Autowired
    private NiveauRepository repository;
    @Override
    public List<Niveau> listNiveaux() {
        return repository.findAll();
    }

    @Override
    public Niveau getNiveau(Long idNiveau) {
        return repository.getOne(idNiveau);
    }
}
