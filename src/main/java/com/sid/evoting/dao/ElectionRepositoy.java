package com.sid.evoting.dao;

import com.sid.evoting.entities.Election;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ElectionRepositoy extends JpaRepository<Election, Long> {
  //en cas de search
   @Query("select e from Election e where e.nomElection like:mc")
   public Page<Election> chercherElection(@Param("mc") String mc, Pageable page);
   //election gere par un superviseur
    @Query("select e from Election e where e.superviseur.idUser=:id")
    public Page<Election> getElections(@Param("id") Long id, Pageable pageable);

    @Query("select e from Election e , Superviseur s where e.superviseur.idUser=s.idUser and s.idUser=:id")
    public Election getElectionSuper(@Param("id") Long id);

    //avoir l'election ouvert
    @Query("select e from Election e where e.ouvert = true ")
    public Election getElectionOuvert();

}
