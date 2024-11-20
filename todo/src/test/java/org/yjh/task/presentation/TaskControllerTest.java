package org.yjh.task.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.yjh.task.application.TaskService;
import org.yjh.task.domain.Task;
import org.yjh.task.domain.TaskStatus;
import org.yjh.task.domain.command.TaskCreate;
import org.yjh.task.domain.command.TaskUpdate;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @DisplayName("할 일을 등록한다.")
    @Test
    void createTask() throws Exception {
        // given
        TaskCreate taskCreate = TaskCreate.builder()
                .title("TEST TITLE")
                .description("TEST DESCRIPTION")
                .dueDate(LocalDate.parse("2999-12-31"))
                .build();

        // stubbing
        Task task = Task.builder()
                .id(1L)
                .title("TEST TITLE")
                .description("TEST DESCRIPTION")
                .status(TaskStatus.TODO)
                .dueDate(Date.valueOf(LocalDate.of(2999, 12, 31)))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        when(taskService.create(any(TaskCreate.class)))
                .thenReturn(task);

        // when
        var result = mockMvc.perform(post("/tasks")
                .content(objectMapper.writeValueAsString(taskCreate))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @DisplayName("할 일을 등록할 때 제목은 필수값이다.")
    @Test
    void createTaskWithoutTitle() throws Exception {
        // given
        TaskCreate taskCreate = TaskCreate.builder()
                .description("TEST DESCRIPTION")
                .dueDate(LocalDate.parse("2999-12-31"))
                .build();

        // when
        var result = mockMvc.perform(post("/tasks")
                .content(objectMapper.writeValueAsString(taskCreate))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @DisplayName("할 일을 등록할 때 내용은 필수값이다.")
    @Test
    void createTaskWithoutDescription() throws Exception {
        // given
        TaskCreate taskCreate = TaskCreate.builder()
                .title("TEST TITLE")
                .dueDate(LocalDate.parse("2999-12-31"))
                .build();

        // when
        var result = mockMvc.perform(post("/tasks")
                .content(objectMapper.writeValueAsString(taskCreate))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @DisplayName("특정 할 일을 조회한다.")
    @Test
    void retrieveTask() throws Exception {
        // stubbing
        Task task = Task.builder()
                .id(1L)
                .title("TEST TITLE")
                .description("TEST DESCRIPTION")
                .status(TaskStatus.TODO)
                .dueDate(Date.valueOf(LocalDate.of(1999, 12, 31)))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        when(taskService.retrieve(any(Long.class)))
                .thenReturn(task);

        // when
        var result = mockMvc.perform(get("/tasks/1"));

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @DisplayName("특정 마감일에 해당하는 할일 목록을 조회한다.")
    @Test
    void retrieveTaskByDueDate() throws Exception {
        // stubbing
        when(taskService.retrieveAll(any(Optional.class)))
                .thenReturn(List.of());

        // when
        var result = mockMvc.perform(get("/tasks?dueDate=2999-12-31"));

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isArray());
    }

    @DisplayName("특정 마감일없이 전체 할일 목록을 조회한다.")
    @Test
    void retrieveTaskWithoutDueDate() throws Exception {
        // stubbing
        when(taskService.retrieveAll(any(Optional.class)))
                .thenReturn(List.of());

        // when
        var result = mockMvc.perform(get("/tasks"));

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isArray());
    }

    @DisplayName("특정 상태에 해당하는 할일 목록을 반환한다.")
    @Test
    void retrieveTaskByStatus() throws Exception {
        // stubbing
        when(taskService.retrieveAllByStatus(any(TaskStatus.class)))
                .thenReturn(List.of());

        // when
        var result = mockMvc.perform(get("/tasks/status/TODO"));

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isArray());
    }

    @DisplayName("할 일을 수정한다.")
    @Test
    void updateTask() throws Exception {
        // given
        TaskUpdate taskUpdate = TaskUpdate.builder()
                .title("TEST TITLE")
                .description("TEST DESCRIPTION")
                .dueDate(LocalDate.parse("2999-12-31"))
                .build();

        // stubbing
        Task task = Task.builder()
                .id(1L)
                .title("TEST TITLE")
                .description("TEST DESCRIPTION")
                .status(TaskStatus.TODO)
                .dueDate(Date.valueOf(LocalDate.of(2999, 12, 31)))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        when(taskService.update(any(Long.class), any(TaskUpdate.class)))
                .thenReturn(task);

        // when
        var result = mockMvc.perform(put("/tasks/1")
                .content(objectMapper.writeValueAsString(taskUpdate))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @DisplayName("할 일을 삭제한다.")
    @Test
    void deleteTask() throws Exception {
        // when
        var result = mockMvc.perform(delete("/tasks/1"));

        // then
        result.andDo(print());
        result.andExpect(status().isOk());
    }
}


















































