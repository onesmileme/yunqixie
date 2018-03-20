package com.yunqixie.utils;

import com.alibaba.fastjson.JSONObject;
import com.yunqixie.common.BaseResponseModel;

public class ResponseUtil {

    public static  String successWithModel(Object object){

        BaseResponseModel model = new BaseResponseModel(0,object);
        return JSONObject.toJSONString(model);
    }

    public  static  String failed(int code , String msg){

        BaseResponseModel model = new BaseResponseModel(code,msg);
        return JSONObject.toJSONString(model);
    }
}
