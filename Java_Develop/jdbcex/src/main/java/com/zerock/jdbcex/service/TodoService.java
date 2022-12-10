package com.zerock.jdbcex.service;

import com.zerock.jdbcex.dao.TodoDAO;
import com.zerock.jdbcex.domain.TodoVO;
import com.zerock.jdbcex.dto.TodoDTO;
import com.zerock.jdbcex.util.MapperUtil;
import org.modelmapper.ModelMapper;

public enum TodoService {
    INSTNACE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // 등록
    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        System.out.println("todoVO: " + todoVO);

        dao.insert(todoVO);
    }
}
