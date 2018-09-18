package com.sid.evoting.dao;

import com.sid.evoting.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement,Long> {
    //retrouver un departement depuis son nom
    public Departement findDepartementByNomDep(String nomDep);

}
