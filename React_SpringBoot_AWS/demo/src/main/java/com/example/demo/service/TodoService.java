package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    // 검증(CRUD 공통 사용)
    public void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Unknown User.");
        }
    }

    // 생성
    public List<TodoEntity> create(final TodoEntity entity) {
     // 검증
     validate(entity);

     repository.save(entity);

     log.info("Entity Id : {} is saved.", entity.getId());

     return repository.findByUserId(entity.getUserId());
    }
}
