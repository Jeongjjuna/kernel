package org.yjh.task.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.TaskStatus;

import java.sql.Date;
import java.sql.Timestamp;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "TASK")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private Date dueDate;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;

    public static TaskEntity from(Task task) {
        final TaskEntity entity = new TaskEntity();
        entity.id = task.getId();
        entity.title = task.getTitle();
        entity.description = task.getDescription();
        entity.status = task.getStatus();
        entity.dueDate = task.getDueDate();
        entity.createdAt = task.getCreatedAt();
        entity.updatedAt = task.getUpdatedAt();
        return entity;
    }

    public Task toDomain() {
        return Task.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status)
                .dueDate(dueDate)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
