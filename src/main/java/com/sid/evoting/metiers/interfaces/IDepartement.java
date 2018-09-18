package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Departement;

import java.util.List;

public interface IDepartement {
    public List<Departement> listDepartement();
    public Departement getDepartement(Long id);
}
