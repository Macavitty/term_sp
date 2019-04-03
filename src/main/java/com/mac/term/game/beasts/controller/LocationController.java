package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("map")
public class LocationController {

    private LocationRepo locationRepo;
    private CreatureRepo creatureRepo;
    private BeastGenerator beastGenerator;

    @Autowired
    LocationController(LocationRepo locationRepo, BeastGenerator beastGenerator, CreatureRepo creatureRepo){
        this.locationRepo = locationRepo;
        this.beastGenerator = beastGenerator;
        this.creatureRepo = creatureRepo;
    }


    @GetMapping("/{l}")
    public Optional<Location> loc(@PathVariable("l") Integer l, @AuthenticationPrincipal User user){
        Map<Object, Object> ret = new HashMap<>();
        Set<Creature> enemies = beastGenerator.generateEnemies(l, user.getId(), creatureRepo,locationRepo );
        ret.put("enemies", enemies);
        return locationRepo.findById(l);
    }


}
