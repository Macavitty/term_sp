package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StartController {

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user){
        Map<Object, Object> m = new HashMap<>();
        m.put("profile", user);
        model.addAttribute("magic_data", m);
        model.addAttribute("user_info", user);
        return "index";
    }
}
