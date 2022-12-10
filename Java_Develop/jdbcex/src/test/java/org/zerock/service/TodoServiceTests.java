package org.zerock.service;

import com.zerock.jdbcex.dto.TodoDTO;
import com.zerock.jdbcex.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTNACE;
    }

    // 등록
    @Test
    public void testRegister() throws Exception {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        todoService.register(todoDTO);
    }
}
