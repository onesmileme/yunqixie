package com.yunqixie.domain.dto;

import lombok.Data;

@Data
public class RelationDTO {

    public static final int RELATION_A_FOLLOW_B = 0X1;
    public static final int RELATION_B_FOLLOW_A = 0X2;
    public static final int RELATION_FOLLOW_EACHOTHER = 0X3;

    private  int a_uid;
    private  int b_uid;
    /**
     * 0x1 a follow b
     * 0x2 b follow a
     * 0x3 follow eachother
     */
    private  int type;
}
