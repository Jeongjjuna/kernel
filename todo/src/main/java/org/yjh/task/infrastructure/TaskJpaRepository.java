package org.yjh.task.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yjh.task.infrastructure.entity.TaskEntity;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
}
