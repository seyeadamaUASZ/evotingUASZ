package com.sid.evoting.controller;

import com.sid.evoting.entities.Notification;
import com.sid.evoting.metiers.interfaces.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private INotification iNotification;
    @PostMapping("/saveNotif")
    public String saveNotification(@Valid Notification notification){
        notification.setDatenotif(new Date());
        notification.setRecent(1);
        iNotification.saveNotif(notification);
        return "redirect:/menu_superviseur";
    }

    //nombre notifications
    @GetMapping("/nombrenotif")
    @ResponseBody
    public Integer nombre(Model model){
        return iNotification.nombreNotif();
    }

}
