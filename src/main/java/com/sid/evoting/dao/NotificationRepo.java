package com.sid.evoting.dao;

import com.sid.evoting.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepo extends JpaRepository<Notification,Long> {
    //compter le nombre de notification
    @Query("select count(n) from Notification n where n.recent=1")
    public Integer nombreNotif();
    //mettre la notification non recent
    @Query("update Notification n set n.recent=0 where n.id_notif=id")
    public void nonRecent(@Param("id") Long id);
}
