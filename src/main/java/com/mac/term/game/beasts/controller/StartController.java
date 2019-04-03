package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.CreatureRepo;
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

    @Autowired
    public StartController(CreatureRepo creatureRepo){
        this.creatureRepo = creatureRepo;
    }

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user){
        Map<Object, Object> m = new HashMap<>();
        Map<Object, Object> mm = new HashMap<>();

        List<Creature> set = creatureRepo.findByOwner(user);
        Map<Object, Object> b = new HashMap<>();
        for (int i = 0; i < set.size(); i++){
            b.put(i, set.get(i));
        }

        m.put("profile", user);
        mm.put("user", user);
        m.put("count", user == null ? 0 : user.getCreatures() == null ? 0 : user.getCreatures().size());
        model.addAttribute("magic_data", m);
        model.addAttribute("user_info", user);
        model.addAttribute("beasts", b);
        return "index";
    }
}
