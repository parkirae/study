package com.zerock.jdbcex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String mid;
    private String mpw;
    private String mname;
    private String uuid;
}
