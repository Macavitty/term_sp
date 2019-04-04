package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.ButtlePhraseRepo;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoControl {
    UserRepo userRepo;

    @Autowired
    public UserInfoControl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    public void moneyChangedTo(Integer newMoney, User u){
        String status = u.getDescription();
        if (newMoney == 0) {
            u.setDescription(status + "Особенности: " + u.getNick() + " не избегает сражений.");
        } else if (newMoney >= 1000) {
            u.setDescription("Кажется " + u.getNick() + " скоро сможет выкупить весь бестиарий.");
        } else {
            u.setDescription("Иногда єтого героя можно застать в таверне \"Дпса\" ");
        }
        userRepo.save(u);
    }

    public void created(String userId){
        User u = userRepo.findById(userId).orElse(null);
        assert u != null;
        u.setDescription("Герой явился.");
        userRepo.save(u);
    }
}
