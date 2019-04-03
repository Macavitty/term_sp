package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import com.mac.term.game.beasts.repository.CreatureRepo;
import com.mac.term.game.beasts.repository.LocationRepo;
import com.mac.term.game.beasts.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BeastGenerator {

    public BeastGenerator() {
    }

    public Set<Creature> generateEnemies(int locationId, String userId, CreatureRepo creatureRepo, LocationRepo locationRepo) {
//        List<Creature> fit = creatureRepo.findAllAndOwnerIdNotAndAndLocation(userId, locationRepo.findById(locationId));
//        Set<Creature> ret = new HashSet<>();
//        for (int i = 0; i < 6; i++){
//            ret.add(fit.get(rand(0, fit.size() - 1)));
//        }
//        return ret;
        return new HashSet<>();
    }

    public Set<Creature> generateForNewUser(String userId, CreatureRepo creatureRepo, UserRepo userRepo) {
        User u = userRepo.findById(userId).orElse(null);
        User no = userRepo.findById("no").orElse(null);
        if (u != null && no != null) {
            List<Creature> fit = creatureRepo.findByOwner(no);
            Set<Creature> ret = new HashSet<>();
            for (int i = 0; i < 6; i++) {
                Creature c = fit.get(rand(0, fit.size() - 1));
                if (ret.add(c)) {
                    c.setOwner(u);
                    creatureRepo.save(c);
                }
            }
            return ret;
        } else {
            return null;
        }
    }

    private int rand(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }
}
