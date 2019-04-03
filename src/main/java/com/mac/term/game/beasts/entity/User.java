package com.mac.term.game.beasts.entity;

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

    Integer level;

    @Column(name = "auth_via")
    String authVia;

    @Column(name = "last_visit")
    LocalDateTime lastVisit;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Creature> creatures = new HashSet<>();
}
