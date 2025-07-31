package com.smokingcessation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "daily_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    private LocalDate taskDate;

    @OneToMany(mappedBy = "dailyTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskItem> checklist;

    public double getCompletionPercentage() {
        if (checklist == null || checklist.isEmpty()) return 0;
        long done = checklist.stream().filter(TaskItem::isCompleted).count();
        return (done * 100.0) / checklist.size();
    }
}
