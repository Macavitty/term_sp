package com.mac.term.game.beasts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "usr")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class User implements Serializable {
    @Id
    String id;

    @Column(name = "nickname", unique = true)
    String nick;

    String icon;

    String email;

    String gender;

    Integer money;

    Integer exp;

    Integer level;

    @Column(name = "description")
    String description;

    @Column(name = "authVia")
    String authVia;

    @Column(name = "last_visit")
    LocalDateTime lastVisit;
}
