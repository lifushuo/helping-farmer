package com.sky.controller.user;

import com.sky.dto.UserPutDto;
import com.sky.result.Result;
import com.sky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping
    Result updateUser(@RequestBody UserPutDto userPutDto) {
        Result result = userService.updateUser(userPutDto);
        return result;

    }


    @GetMapping
    Result getList() {

        Result result = userService.getInfo();
        return result;


    }


}
