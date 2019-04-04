package com.mac.term.game.beasts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "buttle_phrase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ButtlePhrase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Boolean is_win;
    String phrase;
}
