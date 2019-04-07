package com.mac.term.game.beasts.repository;

import com.mac.term.game.beasts.entity.Battle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepo extends CrudRepository<Battle, Long> {
}
