package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.ButtlePhraseRepo;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

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

        u.setExp((int)vn*u.getCreatures().size()/3);
        userRepo.save(u);
    }
}
