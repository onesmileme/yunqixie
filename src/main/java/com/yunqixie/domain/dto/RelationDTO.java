package com.yunqixie.domain.dto;

import lombok.Data;

@Data
public class RelationDTO {

    private  int a_uid;
    private  int b_uid;
    /**
     * 0x1 a follow b
     * 0x2 b follow a
     * 0x3 follow eachother
     */
    /
    private  int type;
}
