package com.ssafy.sopy.controller;

import com.ssafy.sopy.dto.UserDto;
import com.ssafy.sopy.dto.UserReqDto;
import com.ssafy.sopy.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public Object join(UserReqDto param) throws IOException {
        return userService.join(param);
    }
    @PostMapping("/login")
    public Object login(@Valid @RequestBody UserDto param){
        return userService.login(param);
    }
}
