package org.yjh.task.domain.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

public record TaskCreate(
        String title,
        String description,
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dueDate
) {
}