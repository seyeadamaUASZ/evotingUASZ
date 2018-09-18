package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.Notification;

import java.util.List;

public interface INotification {
    //envoyer notification
    public Notification saveNotif(Notification notification);
    //nombre de notif
    public Integer nombreNotif();
    public List<Notification> notifs();

}
