package org.yjh.task.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.command.TaskCreate;
import org.yjh.task.infrastructure.TaskRepository;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public Task create(TaskCreate taskCreate) {
        Task task = Task.createFrom(taskCreate);
        return taskRepository.save(task);
    }
}
