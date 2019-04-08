package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.BattlePhrase;
import com.mac.term.game.beasts.repository.ButtlePhraseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BattleSteps {

    private CleverRandomizer randomizer;
    private ButtlePhraseRepo phraseRepo;

    @Autowired
    public void setRandomizer(CleverRandomizer randomizer) {
        this.randomizer = randomizer;
    }

    @Autowired
    public void setPhraseRepo(ButtlePhraseRepo phraseRepo) {
        this.phraseRepo = phraseRepo;
    }

    public List<Strike> letTheBattleBe(int enemies){
        List<Strike> strikes = new ArrayList<>();
        int user = 100, enemy = 100;
        while (user > 0  && enemy > 0){
            Strike s = generateStrike();
            int d = s.damage;
            System.out.println(d);
            if (d > 0) {
                enemy -= d;
                if (enemy < 0){
                    enemy += d;
                    s.damage = enemy;
                    System.out.println("new enemy d " + -enemy);
                    enemy = 0;
                }
            }
            else {
                user += d;
                if (user < 0){
                    user -=d;
                    s.damage = -user;
                    System.out.println("new user d " + user);
                    user = 0;
                }
            }
            strikes.add(s);
        }
        return strikes;
    }

    public Strike generateStrike() {
        int n = randomizer.rand(1, (int) phraseRepo.count());
        BattlePhrase p = phraseRepo.findById(n).orElseGet(() -> {
            BattlePhrase np = new BattlePhrase();
            np.setPhrase("Произошло что-то непонятное, но мы то знаем, чот кое-то потерял очки здоровья");
            np.setIs_win(true);
            return np;
        });
        phraseRepo.save(p);
        Strike s = new Strike();
        s.msg = p.getPhrase().equals("") ? "Произошло что-то непонятное, но мы то знаем, чот кто-то потерял очки здоровья" : p.getPhrase();
        s.damage = randomizer.rand(20, 40);
        s.damage = p.getIs_win() ? s.damage : -s.damage;
        return s;
    }


}
