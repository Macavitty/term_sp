package com.mac.term.game.beasts.controller;


import com.mac.term.game.beasts.entity.ButtlePhrase;
import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
import com.mac.term.game.beasts.repository.ButtlePhraseRepo;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("fight")
public class GameController {
    private LocationRepo locationRepo;
    private CreatureRepo creatureRepo;
    private BeastGenerator beastGenerator;
    private ButtlePhraseRepo phraseRepo;
    private UserRepo userRepo;

    @Autowired
    GameController(LocationRepo locationRepo, BeastGenerator beastGenerator, CreatureRepo creatureRepo, ButtlePhraseRepo phraseRepo, UserRepo userRepo){
        this.locationRepo = locationRepo;
        this.beastGenerator = beastGenerator;
        this.creatureRepo = creatureRepo;
        this.phraseRepo = phraseRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/{l}")
    public Optional<Location> loc(@PathVariable("l") Integer l, @AuthenticationPrincipal User user){
        Map<Object, Object> ret = new HashMap<>();
        Set<Creature> enemies = beastGenerator.generateEnemies(l, user.getId(), creatureRepo,locationRepo );
        List<Strike> strikes = new ArrayList<>();
        int i = 100*enemies.size();
        while (i > 0){
            Strike strike = generateStrike();
            strikes.add(strike);
            i -= Math.abs(strike.damage);
        }
        ret.put("enemies", enemies);
        ret.put("msgs", strikes);
        return locationRepo.findById(l);
    }

    @GetMapping("/{pay}/{cost}")
    public void pay(@PathVariable("pay") String pa, @PathVariable("cost") Integer cost, @AuthenticationPrincipal User user){
        int curMoney = user.getMoney();
        user.setMoney(curMoney - cost);
        userRepo.save(user);
    }

    private class Strike{
        Integer damage;
        String msg;
    }
    private int rand(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private Strike generateStrike(){
        int n = rand(1, (int)phraseRepo.count());
        ButtlePhrase p = phraseRepo.findById(n).orElse(new ButtlePhrase());
        Strike s = new Strike();
        s.msg = p.getPhrase().equals("") ? "Произошло что-то непонятное, но мы то знаем, чот кто-то потерял очки здоровья" : p.getPhrase();
        s.damage = rand(1, 20);
        s.damage =  p.getIs_win() ? s.damage : -s.damage;
        return s;
    }
}
