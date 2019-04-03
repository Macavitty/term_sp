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
//        List<Creature> fit = creatureRepo.findAllAndOwnerIdNotAndLocation(userId, locationRepo.findById(locationId).orElse(null));
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
            System.out.println("fit: " + fit.size());
            if (fit.size() == 0) return new HashSet<>();
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
            System.out.println("user null");
            return new HashSet<>();
        }
    }

    private int rand(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }
}
