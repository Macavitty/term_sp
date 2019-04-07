package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BandsStore;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("map")
public class LocationController {

    private BandsStore bandsStore;
    private BeastGenerator beastGenerator;

    @Autowired
    public void setBandsStore(BandsStore bandsStore) {
        this.bandsStore = bandsStore;
    }

    @GetMapping("/{l}")
    public Map<Object, Object> loc(@PathVariable("l") Integer l, @AuthenticationPrincipal User user) {
        Map<Object, Object> ret = new HashMap<>();
        beastGenerator.generateEnemies(user.getId());
        Set<Creature> enemies = bandsStore.getEnemies(user.getId());
        ret.put("enemies", enemies);
        return ret;
    }

    @Autowired
    public void setBeastGenerator(BeastGenerator beastGenerator) {
        this.beastGenerator = beastGenerator;
    }
}
