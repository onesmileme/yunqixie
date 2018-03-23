package com.yunqixie.utils;

import com.alibaba.fastjson.JSONObject;
import com.yunqixie.domain.dto.UserDTO;

public class JSONConverter {

    public static  String convertUserToJson(UserDTO user ){


        return JSONObject.toJSONString(user);
    }
}
