package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

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
}
