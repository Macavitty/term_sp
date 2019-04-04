package com.mac.term.game.beasts.repository;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends CrudRepository<Location, Integer> {
    List<Location> findAll();
    Location findByName(String name);
}
