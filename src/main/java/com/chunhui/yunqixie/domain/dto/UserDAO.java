package com.chunhui.yunqixie.domain.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserDAO {

    private int uid;
    private String openid;
    private String nickname;
    private String avatar;
    private String country;
    private String province;
    private String city;
    private int    sex;
    private int    ctime;
    private int    role;
    private Timestamp birthday;
    private String mobile;
    private int    is_rebot;

}
