package com.mac.term.game.beasts.game_utils;

import com.mac.term.game.beasts.entity.Creature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
//@Scope(value = "session")
public class BandsStore implements Serializable {

    private Map<String, Set<Creature>> enemies = new HashMap<>();
    private Map<String, Set<Creature>> active = new HashMap<>();
    private Map<String, Set<Creature>> passive = new HashMap<>();


    public Set<Creature> getEnemies(String userId) {
        return enemies.get(userId);
    }

    public void setEnemies(String userId, Set<Creature> creatures) {
        enemies.put(userId, creatures);
    }

    public void setEnemies(String userId, List<Creature> creatures) {
        enemies.put(userId, new HashSet<>(creatures));
    }

    public Set<Creature> getActive(String userId) {
        return active.get(userId);
    }

    public void setActive(String userId, Set<Creature> creatures) {
        active.put(userId, creatures);
    }

    public void setActive(String userId, List<Creature> creatures) {
        active.put(userId, new HashSet<>(creatures));;
    }

    public Set<Creature> getPassive(String userId) {
        return passive.get(userId);
    }

    public void setPassive(String userId, Set<Creature> creatures) {
        passive.put(userId, creatures);;
    }

    public void setPassive(String userId, List<Creature> creatures) {
        passive.put(userId, new HashSet<>(creatures));;
    }
}
