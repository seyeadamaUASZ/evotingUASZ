package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Filiere;

import java.util.List;

public interface IFiliere {
    public List<Filiere> listFiliere();
    public Filiere getFiliere(Long id);
}
