package com.smokingcessation.dto.coachtask;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyTaskDTO {
    private Long userId;
    private LocalDate taskDate;
    private List<TaskItemDTO> checklist;
}
