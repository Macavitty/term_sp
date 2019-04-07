package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BandsStore;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
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
    private LocationRepo locationRepo;
    private BandsStore bandsStore;
    private BeastGenerator beastGenerator;

    @Autowired
    public StartController(CreatureRepo creatureRepo, LocationRepo locationRepo, BandsStore bandsStore, BeastGenerator beastGenerator){
        this.creatureRepo = creatureRepo;
        this.locationRepo = locationRepo;
        this.beastGenerator = beastGenerator;
        this.bandsStore = bandsStore;
    }

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user){
        Map<Object, Object> m = new HashMap<>();

        Map<Object, Object> lastMap = new HashMap<>();

        List<Location> locations = locationRepo.findAll();
        List<Creature> allCreatures = creatureRepo.findAllByOrderByCost();

        if (user != null){
            beastGenerator.sliceToActivePassive(user.getId());
            System.out.println("USER IS NOT NULL, GOT ACTIVE");
            lastMap.put("active", bandsStore.getActive(user.getId()));
            lastMap.put("passive", bandsStore.getPassive(user.getId()));
            m.put("count", user.getCreatures() == null ? 0 : user.getCreatures().size());
        }
        lastMap.put("locs", locations);
        lastMap.put("all_creatures", allCreatures);

        m.put("profile", user);

        model.addAttribute("magic_data", m);
        model.addAttribute("user_info", user);
        model.addAttribute("another", lastMap);
        return "index";
    }


}
