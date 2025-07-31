package com.smokingcessation.controller;

import com.smokingcessation.dto.coachtask.DailyTaskDTO;
import com.smokingcessation.service.DailyTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/coach/tasks")
@RequiredArgsConstructor
public class CoachChecklistController {
    private final DailyTaskService dailyTaskService;

    @PostMapping("/assign-checklist")
    public ResponseEntity<?> assignChecklist(@RequestBody DailyTaskDTO dto) {
        dailyTaskService.assignChecklist(dto);
        return ResponseEntity.ok("Checklist assigned and email sent.");
    }

    @PatchMapping("/item/{id}")
    public ResponseEntity<?> updateItemStatus(@PathVariable Integer id, @RequestParam boolean completed) {
        dailyTaskService.updateTaskItemStatus(id, completed);
        return ResponseEntity.ok("Item updated.");
    }

    @GetMapping("/completion-rate")
    public ResponseEntity<?> getCompletionRate(@RequestParam Integer userId, @RequestParam String date) {
        double rate = dailyTaskService.getCompletionRate(userId, LocalDate.parse(date));
        return ResponseEntity.ok("Completion: " + rate + "%");
    }
}
