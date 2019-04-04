package com.mac.term.game.beasts.repository;

import com.mac.term.game.beasts.entity.Creature;
import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatureRepo extends CrudRepository<Creature, Integer> {
    List<Creature> findAllByOrderByCost();
    List<Creature> findByOwner(User owner);
    List<Creature> findAllByOwnerIdIsNot(String ownerId);
}
