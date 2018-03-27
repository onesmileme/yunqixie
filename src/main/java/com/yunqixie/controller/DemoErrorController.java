package com.yunqixie.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("error")
public class DemoErrorController implements ErrorController{

    public String getErrorPath(){
        return "error";
    }

    @RequestMapping("/error")
    public String hahaInfo(){

        return "not found page";
    }

    @RequestMapping("/info")
    public String info(){
        return "INFO";
    }
}
