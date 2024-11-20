package org.yjh.task.application.port;

import org.yjh.task.domain.Task;
import org.yjh.task.domain.TaskStatus;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);

    Optional<Task> findById(Long id);

    List<Task> findAllByDueDate(Date dueDate);

    List<Task> findAll();

    List<Task> findAllByStatus(TaskStatus status);

    void deleteById(Long id);
}
