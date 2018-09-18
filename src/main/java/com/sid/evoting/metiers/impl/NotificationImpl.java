package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.NotificationRepo;
import com.sid.evoting.entities.Notification;
import com.sid.evoting.metiers.interfaces.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationImpl implements INotification {
    @Autowired
    private NotificationRepo repo;
    @Override
    public Notification saveNotif(Notification notification) {
        return repo.save(notification);
    }

    @Override
    public Integer nombreNotif() {
        return repo.nombreNotif();
    }

    @Override
    public List<Notification> notifs() {
        return repo.findAll();
    }
}
