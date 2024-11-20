package org.yjh.task.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yjh.task.domain.TaskStatus;
import org.yjh.task.infrastructure.entity.TaskEntity;

import java.sql.Date;
import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByDueDate(Date dueDate);

    List<TaskEntity> findAllByStatus(TaskStatus status);
}
