package com.zerock.jdbcex.service;

import com.zerock.jdbcex.dao.TodoDAO;
import com.zerock.jdbcex.domain.TodoVO;
import com.zerock.jdbcex.dto.TodoDTO;
import com.zerock.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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

    // 목록 조회
    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.selectAll();

        log.info("volist...");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
}
