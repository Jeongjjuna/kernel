package org.yjh.task.domain;

import lombok.Builder;
import lombok.Getter;
import org.yjh.task.domain.command.TaskCreate;
import org.yjh.task.domain.command.TaskStatusUpdate;
import org.yjh.task.domain.command.TaskUpdate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Builder
@Getter
public class Task {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Date dueDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static Task createFrom(TaskCreate taskCreate) {
        return Task.builder()
                .title(taskCreate.title())
                .description(taskCreate.description())
                .dueDate(Date.valueOf(taskCreate.dueDate()))
                .status(TaskStatus.TODO)
                .build();
    }

    public void update(TaskUpdate taskUpdate) {
        this.title = Objects.isNull(taskUpdate.title()) ?
                this.title : taskUpdate.title();
        this.description = Objects.isNull(taskUpdate.description()) ?
                this.description : taskUpdate.description();
        this.dueDate = Objects.isNull(taskUpdate.dueDate()) ?
                this.dueDate : Date.valueOf(taskUpdate.dueDate());
    }

    public void updateStatus(TaskStatusUpdate taskStatusUpdate) {
        this.status = TaskStatus.createBy(taskStatusUpdate.status());
    }
}
