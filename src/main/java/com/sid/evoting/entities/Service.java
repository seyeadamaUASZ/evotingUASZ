package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;
    @Column(name = "nom_service")
    private String nomService;
    @OneToMany(mappedBy = "service")
    private Collection<User> users=new ArrayList<>();

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Service(String nomService) {
        this.nomService = nomService;
    }

    public Long getIdService() {
        return idService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public Service() {
    }
}
