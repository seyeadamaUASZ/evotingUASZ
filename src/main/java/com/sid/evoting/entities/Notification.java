package com.sid.evoting.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id_notif;
     private String titre;
     private String message;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
     private Date datenotif;
    private int recent;

    public Notification(String titre, String message) {
        this.titre = titre;
        this.message = message;
        this.datenotif=new Date();
        this.recent = 1;
    }

    public int getRecent() {
        return recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;
    }

    public Notification() {
    }

    public Long getId_notif() {
        return id_notif;
    }

    public void setId_notif(Long id_notif) {
        this.id_notif = id_notif;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatenotif() {
        return datenotif;
    }

    public void setDatenotif(Date datenotif) {
        this.datenotif = datenotif;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id_notif=" + id_notif +
                ", titre='" + titre + '\'' +
                ", message='" + message + '\'' +
                ", datenotif=" + datenotif +
                '}';
    }
}
