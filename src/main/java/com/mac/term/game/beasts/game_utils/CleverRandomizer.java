package com.mac.term.game.beasts.game_utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CleverRandomizer {

    public int rand(int low, int high) {
        Random r = new Random();
        int t = high - low;
        t  = t <= 0 ? 1 : t;
        return r.nextInt(t) + low;
    }
}
