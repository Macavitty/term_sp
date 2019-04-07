package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BeastGenerator {

    private BandsStore bandsStore;
    private CleverRandomizer randomizer;
    private CreatureRepo creatureRepo;
    private UserRepo userRepo;

    public BeastGenerator(BandsStore bandsStore, CleverRandomizer randomizer, UserRepo userRepo, CreatureRepo creatureRepo) {
        this.bandsStore = bandsStore;
        this.randomizer = randomizer;
        this.userRepo = userRepo;
        this.creatureRepo = creatureRepo;

    }

    public Set<Creature> generateEnemies(String userId) {
        List<Creature> fit = creatureRepo.findAllByOwnerIdIsNot(userId);
        Set<Creature> ret = new HashSet<>();
        for (int i = 0; i < 6; i++){
            ret.add(fit.get(randomizer.rand(0, fit.size() - 1)));
        }
        bandsStore.setEnemies(userId, ret);
        return  ret;
    }

    public Set<Creature> generateForNewUser(String userId) {
        System.out.println(userRepo);
        User u = userRepo.findById(userId).orElse(null);
        User no = userRepo.findById("no").orElse(null);
        if (u != null && no != null) {
            List<Creature> fit = creatureRepo.findByOwner(no);
            if (fit.size() == 0) return new HashSet<>();
            Set<Creature> ret = new HashSet<>();

            for (int i = 0; i < 6; i++) {
                Creature c = fit.get(randomizer.rand(0, fit.size() - 1));
                if (ret.add(c)) {
                    c.setOwner(u);
                    creatureRepo.save(c);
                }
            }
            sliceToActivePassive(userId);
            return ret;
        } else {
            sliceToActivePassive(userId);
            return new HashSet<>();
        }
    }

    public void sliceToActivePassive(String userId){
        User u = userRepo.findById(userId).orElse(null);

        List<Creature> total = creatureRepo.findByOwner(u);

        List<Creature> active = new ArrayList<>();
        List<Creature> passive = new ArrayList<>();

        if (total.size() != 0) {
            for (int i = 0; i < total.size() / 2 + 1; i++) {
                active.add(total.get(i));
            }
            for (int i = total.size() / 2 + 1; i < total.size(); i++) {
                passive.add(total.get(i));
            }
        }
        bandsStore.setActive(userId, active);
        bandsStore.setPassive(userId, passive);
    }
}
