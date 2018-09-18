package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.FiliereRepository;
import com.sid.evoting.entities.Filiere;
import com.sid.evoting.metiers.interfaces.IFiliere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereImpl implements IFiliere {
    @Autowired
    private FiliereRepository fili;
    @Override
    public List<Filiere> listFiliere() {
        return fili.findAll();
    }

    @Override
    public Filiere getFiliere(Long id) {
        return fili.getOne(id);
    }
}
