package com.sid.evoting.dao;

import com.sid.evoting.entities.Candidat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
  //rechercher les candidats a partir du username
    @Query("select c from Candidat c where c.username like:mc")
    public Page<Candidat> chercherParMc(@Param("mc") String mc, Pageable pageable);
    //les candidats inscrits
    @Query("select c from Candidat c where c.election.idElection=:ide")
    public Page<Candidat> getCandidats(@Param("ide") Long ide, Pageable pageable);

    //avoir une list de Candidats inscrits
    @Query("select c from Candidat c, Election e where c.election.idElection=e.idElection")
    public List<Candidat> getCandidatsE();

    //les candidats lies à un departement
    @Query("select c from Candidat c, Departement d where c.departement.idDep=d.idDep")
    public List<Candidat> listCandidatDep();

    //candidats lies a un service
    @Query("select c from Candidat c ,Service s where c.service.idService=s.idService")
    public List<Candidat> listCandidatSer();

    //candidats lies a une formation pour un niveau et un departement
    @Query("select c from Candidat c,Departement d,Filiere f,Niveau n where c.departement.idDep = d.idDep and c.filiere.idfiliere=f.idfiliere and c.niveau.idNiv=n.idNiv and n.filiere.idfiliere=f.idfiliere and f.departement.idDep=d.idDep")
    public List<Candidat> listCandidatDepNiv();

    @Query("select c from Candidat c,Filiere f,Niveau n where c.niveau.idNiv=n.idNiv and c.filiere.idfiliere = f.idfiliere and n.filiere.idfiliere=f.idfiliere")
    public List<Candidat> listCandidatsFNiv();

    //candidats appartenant à un departement
    @Query("select c from Candidat c where c.departement.idDep=:idDep")
    public List<Candidat> CandidatsDep(@Param("idDep") Long idDep);

    //paginer les candidats
    @Query("select c from Candidat c where c.name like :mc")
    public Page<Candidat> pageCandidats(@Param("mc") String mc,Pageable pageable);

    //le candidats ayant le plus grand nombre de voix
   /*@Query("select c from Candidat where c.voix >= all(select c.voix from Candidat c)")
   public Candidat getCandidatMostV();*/

   //le nombre de candidats inscrits
  @Query("select count(c.idUser) from Candidat c")
  public Integer nombreCandidats();


}
