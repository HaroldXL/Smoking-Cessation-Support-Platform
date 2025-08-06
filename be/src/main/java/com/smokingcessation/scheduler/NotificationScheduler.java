package com.smokingcessation.scheduler;

import com.smokingcessation.model.User;
import com.smokingcessation.repository.UserRepository;
import com.smokingcessation.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    // Sáng 8h
    @Scheduled(cron = "0 0 8 * * *", zone = "Asia/Ho_Chi_Minh")
    public void sendMorningMotivation() {
        List<User> allUsers = userRepository.findAll();
        notificationService.sendDailyMotivationNotificationToAll(allUsers);
    }

    // Chiều 13h
    @Scheduled(cron = "0 0 13 * * *", zone = "Asia/Ho_Chi_Minh")
    public void sendAfternoonMotivation() {
        List<User> allUsers = userRepository.findAll();
        notificationService.sendDailyMotivationNotificationToAll(allUsers);
    }

    // Chiều 20h
    @Scheduled(cron = "0 0 20 * * *", zone = "Asia/Ho_Chi_Minh")
    public void sendEveningMotivation() {
        List<User> allUsers = userRepository.findAll();
        notificationService.sendDailyMotivationNotificationToAll(allUsers);
    }
}
