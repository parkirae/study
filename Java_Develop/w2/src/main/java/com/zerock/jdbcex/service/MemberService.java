package com.zerock.jdbcex.service;

import com.sun.javadoc.MemberDoc;
import com.zerock.jdbcex.dao.MemberDAO;
import com.zerock.jdbcex.domain.MemberVO;
import com.zerock.jdbcex.dto.MemberDTO;
import com.zerock.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {

    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDTO login(String mid, String mpw) throws Exception {

        MemberVO vo = dao.getWithPassword(mid, mpw);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }

    public void updateUuid(String mid, String uuid) throws Exception {
        dao.updateUuid(mid, uuid);
    }
}
