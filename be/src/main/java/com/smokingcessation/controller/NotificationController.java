package com.smokingcessation.controller;

import com.smokingcessation.model.NotificationLog;
import com.smokingcessation.repository.NotificationLogRepository;
import com.smokingcessation.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/logs/{userId}")
    public List<NotificationLog> getLogsByUser(@PathVariable Long userId) {
        return notificationLogRepository.findByUserId(userId);
    }

    @PostMapping("/test-send")
    public String testSend() {
        notificationService.sendDailyNotifications();
        return "Sent!";
    }

}
