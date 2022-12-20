package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    // 등록
    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("Spring test")
                .dueDate(LocalDate.of(2022, 12, 25))
                .writer("irae")
                .build();

        todoMapper.insert(todoVO);
    }

    // 전체 조회
    @Test
    public void testSelectAll() {
        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));
    }

    // 단건 조회
    @Test
    public void testSelectOne() {
        TodoVO todoVO = todoMapper.selectOne(3L);

        log.info(todoVO);
    }
}
