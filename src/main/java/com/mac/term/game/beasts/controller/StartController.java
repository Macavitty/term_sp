package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class StartController {

    private CreatureRepo creatureRepo;
    private UserRepo userRepo;

    @Autowired
    public StartController(CreatureRepo creatureRepo, UserRepo userRepo ){
        this.creatureRepo = creatureRepo; this.userRepo = userRepo;
    }

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user){
        Map<Object, Object> m = new HashMap<>();
        Map<Object, Object> mm = new HashMap<>();

        Map<Object, Object> lastMap = new HashMap<>();
        lastMap.put("be", creatureRepo.findByOwner(user));
        lastMap.put("size", creatureRepo.findByOwner(user).size());
        m.put("profile", user);
        m.put("count", user == null ? 0 : user.getCreatures() == null ? 0 : user.getCreatures().size());
        model.addAttribute("magic_data", m);
        model.addAttribute("user_info", user);
        model.addAttribute("another", lastMap);
        return "index";
    }
}
