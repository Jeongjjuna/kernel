package org.yjh.task.domain.command;

import lombok.Builder;

@Builder
public record TaskStatusUpdate(String status) {
}
