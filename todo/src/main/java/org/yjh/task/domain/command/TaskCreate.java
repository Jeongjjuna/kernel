package org.yjh.task.domain.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskCreate(
        @NotNull(message = "제목은 필수입니다.")
        String title,

        @NotNull(message = "내용은 필수입니다.")
        String description,

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dueDate
) {
}