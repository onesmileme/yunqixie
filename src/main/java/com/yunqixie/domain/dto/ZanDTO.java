package com.yunqixie.domain.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ZanDTO {

    private int zid;
    private int tid;
    private int uid;
    private int to_uid;
    private String username;
    private Timestamp ctime;
}
