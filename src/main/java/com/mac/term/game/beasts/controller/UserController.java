package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BandsStore;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
public class UserController {
//    private CreatureRepo creatureRepo;
//    private BandsStore bandsStore;
//
//    @Autowired
//    public UserController(CreatureRepo creatureRepo, BandsStore bandsStore ){
//        this.creatureRepo = creatureRepo;
//        this.bandsStore = bandsStore;
//    }
//
//    @GetMapping
//    public Map<Object, Object> acc(Model model, @AuthenticationPrincipal User user){
//
//        Map<Object, Object> ret = new HashMap<>();
//
//        List<Creature> allCreatures = creatureRepo.findAllByOrderByCost();
//
//            ret.put("active", bandsStore.getActive(user.getId()));
//            ret.put("passive", bandsStore.getPassive(user.getId()));
//
//        return ret;
//    }
}
