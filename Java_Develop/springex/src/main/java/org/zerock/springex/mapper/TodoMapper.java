package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    // 등록
    void insert(TodoVO todoVO);
    
    // 전체 조회
    List<TodoVO> selectAll();

    // 단건 조회
    TodoVO selectOne(Long tno);

    // 삭제
    void delete(Long tno);

    // 수정
    void update(TodoVO todoVO);

    // 목록 처리
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    // 전체 개수 조회
    int getCount(PageRequestDTO pageRequestDTO);
}
