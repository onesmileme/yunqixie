package com.chunhui.yunqixie.utils;

import com.alibaba.fastjson.JSONObject;
import com.chunhui.yunqixie.domain.dto.UserDAO;

public class JSONConverter {

    public static  String convertUserToJson(UserDAO user ){


        return JSONObject.toJSONString(user);
    }
}
