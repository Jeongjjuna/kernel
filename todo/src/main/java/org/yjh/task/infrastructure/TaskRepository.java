package org.yjh.task.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.yjh.task.domain.Task;
import org.yjh.task.infrastructure.entity.TaskEntity;

@RequiredArgsConstructor
@Repository
public class TaskRepository {

    private final TaskJpaRepository taskJpaRepository;


    public Task save(Task task) {
        return taskJpaRepository.save(TaskEntity.from(task))
                .toDomain();
    }
}
