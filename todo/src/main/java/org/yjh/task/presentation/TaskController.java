package org.yjh.task.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yjh.task.application.TaskService;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.command.TaskCreate;
import org.yjh.task.presentation.response.TaskResponse;


@RequestMapping("/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     * @param taskCreate 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskCreate taskCreate) {
        Task task = this.taskService.create(taskCreate);
        return ResponseEntity.ok(TaskResponse.from(task));
    }
}
