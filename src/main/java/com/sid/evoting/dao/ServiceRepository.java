package com.sid.evoting.dao;

import com.sid.evoting.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
   public Service findServiceByNomService(String nom);
}
