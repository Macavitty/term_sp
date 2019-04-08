package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("/updateUser")
    public User updateUser(@AuthenticationPrincipal User user){
        System.out.println("update user");
        return user;
    }
}
