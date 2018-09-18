package com.sid.evoting.dao;

import com.sid.evoting.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filiere,Long> {
     //retrouver les filieres depuis leur nom
    public Filiere findFiliereByNomFiliere(String nom);
}
