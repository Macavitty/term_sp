package com.mac.term.game.beasts.controller;


import com.mac.term.game.beasts.entity.ButtlePhrase;
import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.BeastGenerator;
import com.mac.term.game.beasts.game_utils.Strike;
import com.mac.term.game.beasts.game_utils.UserInfoControl;
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

import java.io.Serializable;
import java.util.*;

@RestController
@RequestMapping("fight")
public class GameController {
    private LocationRepo locationRepo;
    private CreatureRepo creatureRepo;
    private BeastGenerator beastGenerator;
    private ButtlePhraseRepo phraseRepo;
    private UserRepo userRepo;
    private UserInfoControl userInfoControl;

    Strike strike = new Strike();

    @Autowired
    GameController(LocationRepo locationRepo, BeastGenerator beastGenerator, CreatureRepo creatureRepo, ButtlePhraseRepo phraseRepo, UserRepo userRepo, UserInfoControl userInfoControl){
        this.locationRepo = locationRepo;
        this.beastGenerator = beastGenerator;
        this.creatureRepo = creatureRepo;
        this.phraseRepo = phraseRepo;
        this.userRepo = userRepo;
        this.userInfoControl = userInfoControl;
    }

    @GetMapping("/{fight}")
    public Map<Object, Object> loc(@AuthenticationPrincipal User user){
        Map<Object, Object> ret = new HashMap<>();
        Set<Creature> enemies = beastGenerator.generateEnemies(user.getId(), creatureRepo);
        List<Strike> strikes = new ArrayList<>();
        int i = 100*enemies.size();
        while (i > 0){
            Strike strike = generateStrike();
            strikes.add(strike);
            i -= Math.abs(strike.damage);
        }
        ret.put("msgs", strikes);
        ret.put("enemies", enemies);
        return ret;
    }

    @GetMapping("/{pay}/{cost}")
    public void pay(@PathVariable("pay") String pa, @PathVariable("cost") Integer cost, @AuthenticationPrincipal User user){
        int curMoney = user.getMoney();
        user.setMoney(curMoney - cost);
        userRepo.save(user);
        userInfoControl.moneyChangedTo(curMoney - cost, user);
    }


    private int rand(int low, int high) {
        Random r = new Random();
        int t = high - low;
        t  = t <= 0 ? 1 : t;
        return r.nextInt(t) + low;
    }

    private Strike generateStrike(){
        int n = rand(1, (int)phraseRepo.count());
        ButtlePhrase p = phraseRepo.findById(n).orElseGet(() -> {
            ButtlePhrase np = new ButtlePhrase();
           np.setPhrase("Произошло что-то непонятное, но мы то знаем, чот кто-то потерял очки здоровья" );
           np.setIs_win(true);
            return np;
        });
        phraseRepo.save(p);
        Strike s = new Strike();
        s.msg = p.getPhrase().equals("") ? "Произошло что-то непонятное, но мы то знаем, чот кто-то потерял очки здоровья" : p.getPhrase();
        s.damage = rand(1, 20);
        s.damage =  p.getIs_win() ? s.damage : -s.damage;
        return s;
    }
}
