package com.mac.term.game.beasts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "battle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Battle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String steps;

    @Column(name = "is_winner")
    boolean isWinner;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime date;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "gamer_id")
    User gamer;
}
