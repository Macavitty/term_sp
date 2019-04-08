package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.Battle;
import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserInfoControl {
    private UserRepo userRepo;

    @Autowired
    public UserInfoControl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void moneyChangedTo(Integer newMoney, String userId){
        User u = userRepo.findById(userId).orElse(null);
        assert u != null;
        String status = u.getDescription();
        if (newMoney == 0) {
            u.setDescription(status + "Особенности: " + u.getNick() + " не избегает сражений.");
        } else if (newMoney >= 1000) {
            u.setDescription("Кажется " + u.getNick() + " скоро сможет выкупить весь бестиарий.");
        } else {
            u.setDescription("Иногда этого героя можно застать в таверне \"Допса\" ");
        }
        userRepo.save(u);
    }

    public void created(String userId){
        User u = userRepo.findById(userId).orElse(null);
        assert u != null;
        System.out.println("явился");
        System.out.println(u.getId());
        u.setDescription("Герой явился.");
        userRepo.save(u);
    }

    public void updateInfoAfterVictory(String userId){
        User u = userRepo.findById(userId).orElse(null);
//        assert u != null;
        int vn =  u.getVictoryNumber();
        vn++;
        u.setVictoryNumber(vn);
        switch (vn) {
            case 5:
                u.setLevel(1);
                break;
            case 11:
                u.setLevel(2);
                break;
            case 22:
                u.setLevel(3);
                break;
            case 36:
                u.setLevel(4);
                break;
            case 40:
                u.setLevel(5);
                break;
        }
        u.setMoney((int)(u.getMoney() + u.getCreatures().size()*1.5));
        u.setExp((int)vn*u.getCreatures().size()/3);
        userRepo.save(u);
    }
    public void saveSteps(User user, List<Strike> strikes, BattleRepo battleRepo) {
        StringBuilder builder = new StringBuilder();
        for (Strike s : strikes){
            builder.append(s.msg);
            builder.append("\n");
        }
        Battle battle = new Battle();
        battle.setDate(LocalDateTime.now());
        battle.setGamer(user);
        battle.setSteps(builder.toString());
        battle.setWinner(strikes.get(strikes.size() - 1).damage > 0);
//        battleRepo.save(battle);
        System.out.println("save battle");

    }
}
