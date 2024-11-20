package org.yjh.task.domain;

import org.yjh.common.exception.BaseException;

import java.util.Arrays;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    ON_HOLD,
    COMPLETED,
    CANCELLED;

    public static TaskStatus createBy(String status) {
        return Arrays.stream(TaskStatus.values())
                .filter(taskStatus -> taskStatus.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new BaseException(String.format("unsupported task status : %s", status)));
    }
}
