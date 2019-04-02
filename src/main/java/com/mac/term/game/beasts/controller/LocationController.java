package com.mac.term.game.beasts.controller;

import com.mac.term.game.beasts.entity.Location;
import com.mac.term.game.beasts.repository.LocationRepo;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("location")
public class LocationController {

    private LocationRepo locationRepo;

    @GetMapping("{l}")
    public Location loc(@PathVariable("l") String l){
        return locationRepo.findByName(l);
    }
}
