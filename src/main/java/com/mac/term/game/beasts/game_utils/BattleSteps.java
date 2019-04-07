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
        int i = 100*enemies;
        while (i > 0){
            Strike strike = generateStrike();
            strikes.add(strike);
            i -= Math.abs(strike.damage);
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
        s.damage = randomizer.rand(1, 40);
        s.damage = p.getIs_win() ? s.damage : -s.damage;
        return s;
    }


}
