package com.mac.term.game.beasts.repository;

import com.mac.term.game.beasts.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends CrudRepository<Location, Long> {
    Location findByName(String name);
}
