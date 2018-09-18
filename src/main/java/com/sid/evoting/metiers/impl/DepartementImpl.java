package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.DepartementRepository;
import com.sid.evoting.entities.Departement;
import com.sid.evoting.metiers.interfaces.IDepartement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementImpl implements IDepartement {
    @Autowired
    private DepartementRepository dep;
    @Override
    public List<Departement> listDepartement() {
        return dep.findAll();
    }

    @Override
    public Departement getDepartement(Long id) {
        return dep.getOne(id);
    }
}
