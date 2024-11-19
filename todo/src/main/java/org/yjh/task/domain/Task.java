package org.yjh.task.domain;

import lombok.Builder;
import lombok.Getter;
import org.yjh.task.domain.command.TaskCreate;

import java.sql.Date;
import java.sql.Timestamp;

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
}
