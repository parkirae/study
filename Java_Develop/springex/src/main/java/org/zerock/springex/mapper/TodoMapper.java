package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;

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
}
