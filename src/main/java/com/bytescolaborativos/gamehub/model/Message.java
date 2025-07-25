package com.bytescolaborativos.gamehub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "messages")
@Table(name = "messages", schema = "game_hub_bc")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "sender_id", nullable = false)
    private UUID senderId;

    @Column (name = "content", length = 300, nullable = false)
    private String content;

    @Column (name = "time_stamp", nullable = false)
    private LocalDateTime timestamp;

    @Column (name = "match_id", nullable = false)
    private  UUID matchId;

    @Column (name = "tournament_id", nullable = false)
    private UUID tournamentId;
}
