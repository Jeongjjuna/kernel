package org.yjh.task.domain.command;

import org.yjh.task.domain.TaskStatus;

public record TaskStatusUpdate(TaskStatus status) {
}
