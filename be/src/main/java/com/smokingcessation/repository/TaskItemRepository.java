package com.smokingcessation.repository;

import com.smokingcessation.model.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskItemRepository extends JpaRepository<TaskItem, Integer> {
}
