package com.zerock.jdbcex.dao;

import com.zerock.jdbcex.domain.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public String getTime() {
        String now = null;

        try(Connection connection = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            ResultSet resultSet = preparedStatement.executeQuery();
            ) {

            resultSet.next();

            now = resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return now;
    }

    public String getTime2() throws Exception {
        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement("select now()");
        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        String now = resultSet.getString(1);

        return now;
    }

    // 등록
    public void insert(TodoVO vo) throws Exception {
        String sql = "INSERT INTO tbl_todo(title, dueDate, finished) VALUES(?, ?, ?)";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());

        preparedStatement.executeQuery();
    }

    // 전체 조회
    public List<TodoVO> selectAll() throws Exception {

        String sql = "SELECT * FROM tbl-todo";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (resultSet.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();

            list.add(vo);
        }

        return list;
    }

    // 단건 조회
    public TodoVO selectOne(Long tno) throws Exception {

        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

    // 삭제
    public void deleteOne(Long tno) throws Exception {

        String sql = "DELETE FROM tbl_todo FROM WHERE tno = ?";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        preparedStatement.executeUpdate();
    }
}
