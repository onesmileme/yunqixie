package com.yunqixie.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserDTO {

    private int uid;
    @JsonIgnore
    private String openid;
    @JsonIgnore
    private String unionid;
    private String nickname;
    @JsonIgnore
    private String wechat_nickname;
    private String avatar;
    private String country;
    private String province;
    private String city;
    private int    sex;
    private int    ctime;
    private int    role;
    private Timestamp birthday;
    private String mobile;
    @JsonIgnore
    private int    is_rebot;


    private String token;
}
