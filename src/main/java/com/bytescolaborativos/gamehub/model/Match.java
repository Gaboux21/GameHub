package com.bytescolaborativos.gamehub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "matchs")
@Table(name = "matchs", schema = "game_hub_bc")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "tournament_id", nullable = false)
    private UUID tournamentId;

    @ManyToOne
    @JoinColumn(name = "player_1_id", nullable = false)
    private User player1;

    @ManyToOne
    @JoinColumn(name = "player_2_id", nullable = false)
    private User player2;

    private Integer player1Score;
    private Integer player2Score;

    @Column (name = "result", nullable = false)
    private Result result;

    @Column (name = "round", nullable = false)
    private int round;
}
