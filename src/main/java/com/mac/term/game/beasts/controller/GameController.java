package com.mac.term.game.beasts.controller;


import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.game_utils.*;
import com.mac.term.game.beasts.repository.BattleRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("fight")
public class GameController {

    private UserRepo userRepo;
    private UserInfoControl userInfoControl;
    private BandsStore bandsStore;
    private BeastGenerator beastGenerator;
    private BattleSteps battleSteps;
    private UserCreaturesControl userCreaturesControl;
    private BattleRepo battleRepo;
    private List<Strike> strikes = new ArrayList<>();

    @Autowired
    public void setBandsStore(BandsStore bandsStore) {
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
    GameController(BeastGenerator beastGenerator, UserRepo userRepo, UserInfoControl userInfoControl, BattleRepo battleRepo) {
        this.beastGenerator = beastGenerator;
        this.userRepo = userRepo;
        this.userInfoControl = userInfoControl;
        this.battleRepo = battleRepo;
    }

    @GetMapping("/{start}")
    public Map<Object, Object> fight(@PathVariable("start") String start, @AuthenticationPrincipal User user, Model model) {
        Map<Object, Object> ret = new HashMap<>();
        Set<Creature> enemies = bandsStore.getEnemies(user.getId());
        enemies = enemies.size() == 0 ? beastGenerator.generateEnemies(user.getId()) : enemies;
        Set<Creature> active = bandsStore.getActive(user.getId());
        strikes = battleSteps.letTheBattleBe(enemies.size());

        System.out.println(strikes);
        ret.put("msgs", strikes);
        ret.put("enemies", enemies);
        ret.put("active", active);
        return ret;
    }

    @GetMapping("/{pay}/{cost}")
    public boolean pay(@PathVariable("pay") String pa, @PathVariable("cost") Integer cost, @AuthenticationPrincipal User user, Model model) {
        int curMoney = user.getMoney();
        if (curMoney - cost >= 0)
            user.setMoney(curMoney - cost);
        userRepo.save(user);
        userInfoControl.moneyChangedTo(curMoney - cost, user.getId());
        userInfoControl.updateInfoAfterVictory(user.getId());
        System.out.println("pay " + cost);
        model.addAttribute("user_info", user);
        return (curMoney - cost >= 0);
    }

    @GetMapping("/saveBeasts")
    public void saveNewCreatures(@AuthenticationPrincipal User user) {
        userCreaturesControl.takeEnemies(user.getId());
        userInfoControl.updateInfoAfterVictory(user.getId());
        System.out.println("save");
    }

    @GetMapping("/saveSteps")
    public void saveSteps(@AuthenticationPrincipal User user) {
        userInfoControl.saveSteps(user, strikes, battleRepo);
        userInfoControl.updateInfoAfterVictory(user.getId());
        System.out.println("save");
    }
}
