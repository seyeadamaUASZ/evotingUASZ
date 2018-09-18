package com.sid.evoting.dao;

import com.sid.evoting.entities.Electeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ElecteurRepository extends JpaRepository<Electeur, Long> {
   //les electeurs lies à un departement
    @Query("select e from Electeur e where e.departement.idDep=:idDep")
    public List<Electeur> listElecteurDep(@Param("idDep") Long idDep);

    //les electeurs ayant vote
    @Query("select e from Electeur e where e.vote=1")
    public List<Electeur> listElecteurVote();

    //les electeurs n'ayant pas encore vote
    @Query("select e from Electeur e where e.vote=0")
    public List<Electeur> listElecteurVoteNon();

    //le nombre d electeur ayant vote
    @Query("select count(e.idUser) from Electeur e where e.vote=1")
    public Integer nombreelecteurV();

    //le nombre d'electeurs n'ayant pas vote
    @Query("select count(e.idUser) from Electeur e where e.vote=0")
    public Integer nombreElecteurNonV();

    @Query("select e from Electeur e,Departement d where e.departement.idDep=d.idDep")
    public List<Electeur> electeursDep();

    //nombre total d'electeurs
    @Query("select count(e.idUser) from Electeur e")
    public Integer nombreTotalElecteur();

    //page electeur avec pagination
    @Query("select e from Electeur e where e.name like :mc")
   public Page<Electeur> listPages(@Param("mc") String mc,Pageable pageable);

    //nombre d'electeurs
 @Query("select count(e.idUser) from Electeur e")
 public Integer nombreElecteur();

}
