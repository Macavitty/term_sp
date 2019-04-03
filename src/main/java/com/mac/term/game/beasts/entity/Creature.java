package com.mac.term.game.beasts.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "creature")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Creature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "integration_lvl")
    Integer integrationLvl;

    @Column(name = "magic_lvl")
    Integer magicLvl;

    String name;

    Integer cost;

    Integer level;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    User owner;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "location_id")
    Location location;
}
