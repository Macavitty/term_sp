package com.mac.term.game.beasts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    String type;

    String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    User owner;

    @JsonIgnore
    @ManyToOne (optional=false)
    @JoinColumn(name = "location_id")
    Location location;
}
