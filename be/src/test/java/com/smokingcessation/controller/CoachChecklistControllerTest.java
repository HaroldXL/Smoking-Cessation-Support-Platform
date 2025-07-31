package com.smokingcessation.controller;
import com.smokingcessation.dto.coachtask.DailyTaskDTO;
import com.smokingcessation.service.DailyTaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class CoachChecklistControllerTest {
    @Mock
    private DailyTaskService dailyTaskService;

    @InjectMocks
    private CoachChecklistController controller;

    public CoachChecklistControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assignChecklist_shouldReturnSuccessMessage() {
        DailyTaskDTO dto = new DailyTaskDTO();
        dto.setUserId(1L);
        dto.setTaskDate(LocalDate.now());

        ResponseEntity<?> response = controller.assignChecklist(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Checklist assigned and email sent.", response.getBody());
        verify(dailyTaskService).assignChecklist(dto);
    }

    @Test
    void updateItemStatus_shouldReturnOk() {
        ResponseEntity<?> response = controller.updateItemStatus(5, true);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Item updated.", response.getBody());
        verify(dailyTaskService).updateTaskItemStatus(5, true);
    }

    @Test
    void getCompletionRate_shouldReturnPercentage() {
        when(dailyTaskService.getCompletionRate(1, LocalDate.parse("2025-08-01")))
                .thenReturn(75.0);

        ResponseEntity<?> response = controller.getCompletionRate(1, "2025-08-01");

        assertEquals("Completion: 75.0%", response.getBody());
        verify(dailyTaskService).getCompletionRate(1, LocalDate.parse("2025-08-01"));
    }
}
