package com.smokingcessation.service;

import com.smokingcessation.dto.coachtask.DailyTaskDTO;
import com.smokingcessation.dto.coachtask.TaskItemDTO;
import com.smokingcessation.model.DailyTask;
import com.smokingcessation.model.TaskItem;
import com.smokingcessation.model.User;
import com.smokingcessation.repository.DailyTaskRepository;
import com.smokingcessation.repository.TaskItemRepository;
import com.smokingcessation.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final TaskItemRepository taskItemRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    public void assignChecklist(DailyTaskDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        dailyTaskRepository.deleteByUserUserIdAndTaskDate(dto.getUserId(), dto.getTaskDate());

        DailyTask task = new DailyTask();
        task.setUser(user);
        task.setTaskDate(dto.getTaskDate());

        List<TaskItem> items = dto.getChecklist().stream().map(itemDto -> {
            TaskItem item = new TaskItem();
            item.setDescription(itemDto.getDescription());
            item.setCompleted(false);
            item.setDailyTask(task);
            return item;
        }).toList();

        task.setChecklist(items);

        dailyTaskRepository.save(task);

        // Gửi email
        emailService.sendDailyChecklistEmail(
                user.getEmail(),
                user.getFullName(),
                dto.getTaskDate(),
                dto.getChecklist().stream().map(TaskItemDTO::getDescription).toList()
        );
    }

    public double getCompletionRate(Integer userId, LocalDate date) {
        return dailyTaskRepository.findByUserUserIdAndTaskDate(userId, date)
                .map(DailyTask::getCompletionPercentage)
                .orElse(0.0);
    }

    public void updateTaskItemStatus(Integer itemId, boolean completed) {
        TaskItem item = taskItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setCompleted(completed);
        taskItemRepository.save(item);
    }
}
