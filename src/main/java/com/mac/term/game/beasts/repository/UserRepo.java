package com.mac.term.game.beasts.repository;

import com.mac.term.game.beasts.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, String> {
}
