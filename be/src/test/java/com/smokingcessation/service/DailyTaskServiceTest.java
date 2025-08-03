package com.smokingcessation.service;

import com.smokingcessation.dto.coachtask.DailyTaskDTO;
import com.smokingcessation.dto.coachtask.TaskItemDTO;
import com.smokingcessation.model.DailyTask;
import com.smokingcessation.model.TaskItem;
import com.smokingcessation.model.User;
import com.smokingcessation.repository.DailyTaskRepository;
import com.smokingcessation.repository.TaskItemRepository;
import com.smokingcessation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DailyTaskServiceTest {
    @InjectMocks
    private DailyTaskService dailyTaskService;

    @Mock
    private DailyTaskRepository dailyTaskRepository;

    @Mock
    private TaskItemRepository taskItemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assignChecklist_shouldSaveTaskAndSendEmail() {
        // Given
        DailyTaskDTO dto = new DailyTaskDTO();
        dto.setUserId(1L);
        dto.setTaskDate(LocalDate.now());

        TaskItemDTO item1 = new TaskItemDTO();
        item1.setDescription("Uống đủ nước");

        TaskItemDTO item2 = new TaskItemDTO();
        item2.setDescription("Không hút thuốc");

        dto.setChecklist(List.of(item1, item2));

        User mockUser = new User();
        mockUser.setUserId(1);
        mockUser.setFullName("Nguyen Van A");
        mockUser.setEmail("a@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // When
        dailyTaskService.assignChecklist(dto);

        // Then
        verify(dailyTaskRepository).deleteByUserUserIdAndTaskDate(1L, dto.getTaskDate());
        verify(dailyTaskRepository).save(any(DailyTask.class));
        verify(emailService).sendDailyChecklistEmail(eq("a@gmail.com"), eq("Nguyen Van A"), eq(dto.getTaskDate()), anyList());
    }

    @Test
    void getCompletionRate_shouldReturnCorrectPercentage() {
        DailyTask task = new DailyTask();
        TaskItem item1 = new TaskItem(null, "A", true, null);
        TaskItem item2 = new TaskItem(null, "B", false, null);
        task.setChecklist(List.of(item1, item2));

        when(dailyTaskRepository.findByUserUserIdAndTaskDate(1, LocalDate.now()))
                .thenReturn(Optional.of(task));

        double percent = dailyTaskService.getCompletionRate(1, LocalDate.now());

        assertEquals(50.0, percent);
    }

    @Test
    void updateTaskItemStatus_shouldUpdateCompletion() {
        TaskItem item = new TaskItem(1, "task", false, null);
        when(taskItemRepository.findById(1)).thenReturn(Optional.of(item));

        dailyTaskService.updateTaskItemStatus(1, true);

        assertTrue(item.isCompleted());
        verify(taskItemRepository).save(item);
    }

}
