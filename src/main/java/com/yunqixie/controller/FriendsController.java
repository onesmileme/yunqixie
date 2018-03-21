package com.yunqixie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("social")
public class FriendsController {

    @RequestMapping(value = "/news")
    public String getMySocialNews(@RequestParam(value = "pn" , defaultValue = "0") int pn ,@RequestParam("uid") int uid){

        
        return null;
    }

}
