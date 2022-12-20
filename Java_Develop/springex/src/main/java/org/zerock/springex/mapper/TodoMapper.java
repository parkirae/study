package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;

public interface TodoMapper {

    String getTime();

    // 등록
    void insert(TodoVO todoVO);
}
