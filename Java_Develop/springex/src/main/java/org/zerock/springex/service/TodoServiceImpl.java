package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    // 등록
    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info(todoVO);

        todoMapper.insert(todoVO);
    }

    // 전체 조회
//    @Override
//    public List<TodoDTO> getAll() {
//
//        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
//                .map(vo -> modelMapper.map(vo, TodoDTO.class))
//                .collect(Collectors.toList());
//
//        return dtoList;
//    }

    // 전체 조회(페이징 처리)
    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    // 단건 조회
    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    // 삭제
    @Override
    public void remove(Long tno) {

        todoMapper.delete(tno);
    }

    // 수정
    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoMapper.update(todoVO);
    }
}
