package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Niveau;

import java.util.List;

public interface INiveau {
    public List<Niveau> listNiveaux();
    public Niveau getNiveau(Long idNiveau);
}
