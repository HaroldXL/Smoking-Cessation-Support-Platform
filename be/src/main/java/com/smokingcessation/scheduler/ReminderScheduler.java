package com.smokingcessation.scheduler;

import com.smokingcessation.model.DailyTask;
import com.smokingcessation.model.User;
import com.smokingcessation.repository.DailyTaskRepository;
import com.smokingcessation.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {
    private final DailyTaskRepository dailyTaskRepository;
    private final EmailService emailService;

    /**
     * Chạy lúc 20:00 hàng ngày
     */
    @Scheduled(cron = "0 0 20 * * *") // 20:00 mỗi ngày
    public void sendIncompleteTaskReminders() {
        LocalDate today = LocalDate.now();
        List<DailyTask> tasks = dailyTaskRepository.findAllByTaskDate(today);

        for (DailyTask task : tasks) {
            double percent = task.getCompletionPercentage();
            if (percent < 100.0) {
                User user = task.getUser();
                emailService.sendReminderCheckListEmail(user.getEmail(), user.getFullName());
                log.info("Sent reminder to: {}", user.getEmail());
            }
        }
    }
}
