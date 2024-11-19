package org.yjh.task.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.TaskStatus;
import org.yjh.task.infrastructure.entity.TaskEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public Task save(Task task) {
        return taskJpaRepository.save(TaskEntity.from(task))
                .toDomain();
    }

    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id)
                .map(TaskEntity::toDomain);
    }

    public List<Task> findAllByDueDate(Date dueDate) {
        return taskJpaRepository.findByDueDate(dueDate)
                .stream()
                .map(TaskEntity::toDomain)
                .toList();
    }

    public List<Task> findAll() {
        return taskJpaRepository.findAll()
                .stream()
                .map(TaskEntity::toDomain)
                .toList();
    }

    public List<Task> findAllByStatus(TaskStatus status) {
        return taskJpaRepository.findAllByStatus(status)
                .stream()
                .map(TaskEntity::toDomain)
                .toList();
    }
}
