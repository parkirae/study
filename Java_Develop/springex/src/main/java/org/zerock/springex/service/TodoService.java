package org.zerock.springex.service;

import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    // 등록
    void register(TodoDTO todoDTO);

    // 전체 조회
    //List<TodoDTO> getAll();
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    // 단건 조회
    TodoDTO getOne(Long tno);

    // 삭제
    void remove(Long tno);

    // 수정
    void modify(TodoDTO todoDTO);
}
