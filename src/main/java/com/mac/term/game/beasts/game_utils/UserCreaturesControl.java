package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserCreaturesControl {
    private BandsStore bandsStore;
    private UserRepo userRepo;
    private CreatureRepo creatureRepo;

    @Autowired
    public void setBandsStore(BandsStore bandsStore) {
        this.bandsStore = bandsStore;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setCreatureRepo(CreatureRepo creatureRepo) {
        this.creatureRepo = creatureRepo;
    }

    public void takeEnemies(String userId) {
        Set<Creature> enemies = bandsStore.getEnemies(userId);
        bandsStore.setEnemies(userId, new HashSet<>());
        User u = userRepo.findById(userId).orElse(null);
        for (Creature e : enemies) {
            e.setOwner(u);
            creatureRepo.save(e);
        }
        System.out.println("take");
    }

}
