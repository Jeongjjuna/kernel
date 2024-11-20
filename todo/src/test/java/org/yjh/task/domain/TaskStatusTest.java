package org.yjh.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yjh.common.exception.BaseException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskStatus 단위테스트")
class TaskStatusTest {

    @DisplayName("지원되지 않는 상태를 생성할 수 없다.")
    @Test
    void createInValidTaskStatus() {
        // given
        String input = "INVALID_STATUS";

        // when & then
        assertThrows(BaseException.class,
                () -> TaskStatus.createBy(input));
    }
}