package com.smokingcessation.repository;

import com.smokingcessation.model.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyTaskRepository extends JpaRepository<DailyTask, Integer> {

    Optional<DailyTask> findByUserUserIdAndTaskDate(Integer userId, LocalDate taskDate);
    void deleteByUserUserIdAndTaskDate(Long userId, LocalDate taskDate);
    List<DailyTask> findAllByTaskDate(LocalDate date);

}
