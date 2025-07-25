package com.bytescolaborativos.gamehub.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity (name = "users")
@Table(name = "users", schema = "game_hub_bc")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "username", length = 150, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El password no tiene un formato válido")
    @Column (name = "password", length = 150, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column (name = "rank", nullable = false)
    private Status rank;

    @Column (name = "points", length = 50, nullable = false)
    private Integer points;
}
