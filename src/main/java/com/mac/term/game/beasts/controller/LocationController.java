package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.repository.LocationRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("map")
public class LocationController {

    private LocationRepo locationRepo;

    @GetMapping("{l}")
    public Optional<Location> loc(@PathVariable("l") Integer l){
        return locationRepo.findById(l);
    }
}
