package org.yjh.task.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yjh.task.application.TaskService;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.TaskStatus;
import org.yjh.task.domain.command.TaskCreate;
import org.yjh.task.domain.command.TaskStatusUpdate;
import org.yjh.task.domain.command.TaskUpdate;
import org.yjh.task.presentation.response.TaskResponse;

import java.util.List;
import java.util.Optional;


@RequestMapping("/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     *
     * @param taskCreate 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskCreate taskCreate) {
        Task task = taskService.create(taskCreate);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    /**
     * 특정 ID에 해당하는 할일을 조회
     *
     * @param id 할일 ID
     * @return ID에 해당하는 할일 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> retrieveOne(@PathVariable("id") Long id) {
        Task task = taskService.retrieve(id);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    /**
     * 특정 마감일에 해당하는 할일 목록을 반환
     *
     * @param dueDate 할일의 마감일
     * @return 마감일에 해당하는 할일 목록
     */
    @GetMapping
    public ResponseEntity<List<TaskResponse>> retrieveAll(@RequestParam("dueDate") Optional<String> dueDate) {
        List<Task> tasks = taskService.retrieveAll(dueDate);

        return ResponseEntity.ok(
                tasks.stream()
                        .map(TaskResponse::from)
                        .toList()
        );
    }

    /**
     * 특정 상태에 해당하는 할일 목록을 반환
     *
     * @param status 할일 상태
     * @return 상태에 해당하는 할일 목록
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> retrieveAllByStatus(@PathVariable("status") TaskStatus status) {
        List<Task> tasks = taskService.retrieveAllByStatus(status);

        return ResponseEntity.ok(
                tasks.stream()
                        .map(TaskResponse::from)
                        .toList()
        );
    }

    /**
     * 특정 ID에 해당하는 할일을 수정
     *
     * @param id         할일 ID
     * @param taskUpdate 수정할 할일 정보
     * @return 수정된 할일 객체
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id,
                                                   @RequestBody TaskUpdate taskUpdate) {
        Task task = taskService.update(id, taskUpdate);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    /**
     * 특정 ID에 해당하는 할일의 상태를 수정
     *
     * @param id               할일 ID
     * @param taskStatusUpdate 수정할 할일 상태 정보
     * @return 수정된 할일 객체
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable("id") Long id,
                                                         @RequestBody TaskStatusUpdate taskStatusUpdate) {
        Task task = taskService.updateStatus(id, taskStatusUpdate);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    /**
     * 특정 ID에 해당하는 할일을 삭제
     *
     * @param id 삭제할 할일 ID
     * @return 삭제 결과를 담은 응답 객체
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
