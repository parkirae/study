package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    // 생성
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user";

            // (1) TodoEntity로 변환합니다.
            TodoEntity entity = TodoDTO.toEntity(dto);

            // (2) id를 null로 초기화합니다.
            entity.setId(null);

            // (3) 임시 유저 아이디를 설정합니다. 추후 SpringSecurity 적용합니다.
            entity.setUserId(temporaryUserId);

            // (4) 서비스를 이용해 Todo 엔티티를 생성한다.
            List<TodoEntity> entities = service.create(entity);

            // (5) 리턴된 엔티티를 Todo 리스트로 변환합니다.
            List<TodoDTO> dtos = entities.stream()
                    .map(TodoDTO::new).collect(Collectors.toList());

            // (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화합니다.
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos).build();

            // (7) ResponseDTO를 리턴합니다.
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // (8) 에러 발생시 error 메시지를 리턴합니다.
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 조회
    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "temporary-user";

        // (1) 서비스 메서드의 retrieve 메서드를 사용해 Todo 리스트를 가져옵니다.
        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        // (2) 리천된 엔티티 리스트를 TodoDTO 리스트로 변환합니다.
        List<TodoDTO> dtos = entities.stream()
                .map(TodoDTO::new).collect(Collectors.toList());

        // (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화합니다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos).build();

        // (7) ResponseDTO를 리턴합니다.
        return ResponseEntity.ok().body(response);
    }

    // 수정
    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary-user";

        // (1) dto를 entity로 변환합니다.
        TodoEntity entity = TodoDTO.toEntity(dto);

        // (2) id를 temporaryUserId로 초기화합니다.
        entity.setUserId(temporaryUserId);

        // (3) entity를 업데이트합니다.
        List<TodoEntity> entities = service.update(entity);

        // (4) 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환합니다.
        List<TodoDTO> dtos = entities.stream()
                .map(TodoDTO::new).collect(Collectors.toList());

        // (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화합니다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos).build();

        // (6) ResponseDTO를 반환합니다.
        return ResponseEntity.ok().body(response);
    }
}
