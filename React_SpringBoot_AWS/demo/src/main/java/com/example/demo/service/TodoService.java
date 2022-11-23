package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // 조회
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    // 수정
    public List<TodoEntity> update(final TodoEntity entity) {
        // (1) 검증
        validate(entity);

        // (2) 엔티티 id 를 이용해 TodoEntity를 가져옵니다.
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        if (original.isPresent()) {
            // (3) 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌웁니다.
            final TodoEntity todo = original.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // (4) 데이터베이스에 새 값을 저장합니다.
            repository.save(todo);
        }

        // (5) 유저의 모든 Todo 리스트를 리턴합니다.
        return retrieve(entity.getUserId());
    }
}
