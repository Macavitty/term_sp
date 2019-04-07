package com.mac.term.game.beasts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "usr")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class User implements Serializable {

    @Id
    String id;

    @Column(name = "nickname")
    String nick;

    String icon;

    String email;

    Integer money;

    Integer exp;

    @Column(name = "victory_number")
    Integer victoryNumber;

    Integer level;

    String description;

    @Column(name = "auth_via")
    String authVia;

    @Column(name = "registered")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime registered;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    Set<Creature> creatures = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "gamer", fetch = FetchType.EAGER)
    List<Battle> battles = new ArrayList<>();
}
