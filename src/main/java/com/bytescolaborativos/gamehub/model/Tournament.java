package com.bytescolaborativos.gamehub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "tournaments")
@Table(name = "tournaments", schema = "game_hub_bc")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "name", length = 150, nullable = false)
    private String name;

    @Column (name = "max_players", nullable = false)
    private int maxPlayers;

    @Column (name = "status", nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "players",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> players = new HashSet<>();
}
