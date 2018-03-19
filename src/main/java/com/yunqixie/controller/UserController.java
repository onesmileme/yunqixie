package com.yunqixie.controller;

import com.yunqixie.domain.dto.UserDAO;
import com.yunqixie.service.UserService;
import com.yunqixie.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/info")
    public String userInfo(@RequestParam("uid") int uid){

        UserDAO userDAO = userService.getUserDAO(uid);

        return ResponseUtil.successWithModel(userDAO);
    }



}
