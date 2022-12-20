package org.zerock.springex.dto;

import java.util.List;

public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    //시작 페이지
    private int start;

    //마지막 페이지
    private int end;

    //이전 페이지 존재 여부
    private boolean prev;

    //다음 페이지 존재 여부
    private boolean next;

    private List<E> dtoList;
}
