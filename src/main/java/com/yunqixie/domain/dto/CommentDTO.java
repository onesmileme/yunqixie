package com.yunqixie.domain.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {

    private int cid;
    private int tid;
    private int from_uid;
    private int to_uid;
    private int to_cid;
    private String content;
    private int is_del;
    private Timestamp ctime;
}
