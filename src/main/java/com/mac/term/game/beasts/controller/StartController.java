package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.Authenticator;
import java.util.*;

@Controller
@RequestMapping("/")
public class StartController {

    private CreatureRepo creatureRepo;
    private UserRepo userRepo;
    private LocationRepo locationRepo;

    @Autowired
    public StartController(CreatureRepo creatureRepo, UserRepo userRepo, LocationRepo locationRepo ){
        this.creatureRepo = creatureRepo; this.userRepo = userRepo;
        this.locationRepo = locationRepo;
    }

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user){
        Map<Object, Object> m = new HashMap<>();
        Map<Object, Object> mm = new HashMap<>();

        Map<Object, Object> lastMap = new HashMap<>();
        List<Creature> total = creatureRepo.findByOwner(user);
        List<Creature> active = new ArrayList<>();
        List<Creature> save = new ArrayList<>();
        if (total.size() != 0) {
            for (int i = 0; i < total.size() / 2 + 1; i++) {
                active.add(total.get(i));
            }
            for (int i = total.size() / 2 + 1; i < total.size(); i++) {
                save.add(total.get(i));
            }
        }
        List<Location> locations = locationRepo.findAll();
        List<Creature> allCreatures = creatureRepo.findAllByOrderByCost();



        lastMap.put("active", active);
        lastMap.put("passive", save);
        lastMap.put("locs", locations);
        lastMap.put("all_creatures", allCreatures);
        m.put("profile", user);
        m.put("count", user == null ? 0 : user.getCreatures() == null ? 0 : user.getCreatures().size());
        model.addAttribute("magic_data", m);
        model.addAttribute("user_info", user);
        model.addAttribute("another", lastMap);
        return "index";
    }
}
