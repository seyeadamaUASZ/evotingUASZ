package com.sid.evoting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_user",discriminatorType = DiscriminatorType.STRING,length = 2)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String username;
	private String name;
	private boolean active;
	private String password;
	private String profession;
	private String adresseU;
	private String telephoneU;
	@ManyToOne
	@JoinColumn(name = "id_dep")
	private Departement departement;
	@ManyToOne
    @JoinColumn(name = "id_service")
	private Service service;
	@ManyToOne
    @JoinColumn(name = "id_niveau")
	private Niveau niveau;
	@ManyToOne
    @JoinColumn(name = "id_filiere")
	private Filiere filiere;

	@JsonIgnore
    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
    @JsonIgnore
    public Service getService() {
        return service;
    }

    @JsonIgnore
    public Niveau getNiveau() {
        return niveau;
    }

	public String getAdresseU() {
		return adresseU;
	}

	public void setAdresseU(String adresseU) {
		this.adresseU = adresseU;
	}

	public String getTelephoneU() {
		return telephoneU;
	}

	public void setTelephoneU(String telephoneU) {
		this.telephoneU = telephoneU;
	}

	public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public void setService(Service service) {
        this.service = service;
    }
    @JsonIgnore
    public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles=new ArrayList<>();
	@JsonIgnore
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
		this.active = active;
	}
    @JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String userName, String name, boolean active, String password) {
		super();
		this.username = userName;
		this.name = name;
		this.active = active;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
