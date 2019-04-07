package com.mac.term.game.beasts.controller;


import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.*;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("fight")
public class GameController {

    private UserRepo userRepo;
    private UserInfoControl userInfoControl;
    private BandsStore bandsStore;
    private BeastGenerator beastGenerator;
    private BattleSteps battleSteps;
    private UserCreaturesControl userCreaturesControl;

    @Autowired
    public void setBandsStore(BandsStore bandsStore){
        this.bandsStore = bandsStore;
    }

    @Autowired
    public void setBattleSteps(BattleSteps battleSteps) {
        this.battleSteps = battleSteps;
    }

    @Autowired
    public void setUserCreaturesControl(UserCreaturesControl userCreaturesControl) {
        this.userCreaturesControl = userCreaturesControl;
    }

    @Autowired
    GameController(BeastGenerator beastGenerator, UserRepo userRepo, UserInfoControl userInfoControl){
        this.beastGenerator = beastGenerator;
        this.userRepo = userRepo;
        this.userInfoControl = userInfoControl;
    }

    @GetMapping("/{fight}/{fight}")
    public Map<Object, Object> fight(@PathVariable("fight") String fight, @AuthenticationPrincipal User user){
        Map<Object, Object> ret = new HashMap<>();
        Set<Creature> enemies = bandsStore.getEnemies(user.getId());
        enemies = enemies.size() == 0 ? beastGenerator.generateEnemies(user.getId()) : enemies;
        List<Strike> strikes = battleSteps.letTheBattleBe(enemies.size());
        ret.put("msgs", strikes);
        ret.put("enemies", enemies);
        return ret;
    }

    @GetMapping("/{pay}/{cost}")
    public void pay(@PathVariable("pay") String pa, @PathVariable("cost") Integer cost, @AuthenticationPrincipal User user){
        int curMoney = user.getMoney();
        user.setMoney(curMoney - cost);
        userRepo.save(user);
        userInfoControl.moneyChangedTo(curMoney - cost, user.getId());
        userInfoControl.updateInfoAfterVictory(user.getId());
    }

    @GetMapping("/{saveBeasts}")
    public void saveNewCreatures(@PathVariable("saveBeasts") String saveBeasts, @AuthenticationPrincipal User user){
        userCreaturesControl.takeEnemies(user.getId());
        userInfoControl.updateInfoAfterVictory(user.getId());
    }


}
