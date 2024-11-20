package org.yjh.task.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.TaskStatus;
import org.yjh.task.domain.command.TaskCreate;
import org.yjh.task.domain.command.TaskStatusUpdate;
import org.yjh.task.domain.command.TaskUpdate;
import org.yjh.task.infrastructure.TaskRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("not exists task id [%d]", id)));
    }

    public List<Task> retrieveAll(Optional<String> dueDate) {
        if (dueDate.isPresent()) {
            return taskRepository.findAllByDueDate(Date.valueOf(dueDate.get()));
        }
        return taskRepository.findAll();
    }

    public List<Task> retrieveAllByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    @Transactional
    public Task create(TaskCreate taskCreate) {
        Task task = Task.createFrom(taskCreate);
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Long taskId, TaskUpdate taskUpdate) {
        Task task = getById(taskId);
        task.update(taskUpdate);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateStatus(Long id, TaskStatusUpdate taskStatusUpdate) {
        Task task = getById(id);
        task.updateStatus(taskStatusUpdate);
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
