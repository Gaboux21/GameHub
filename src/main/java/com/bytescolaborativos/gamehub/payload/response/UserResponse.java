package com.bytescolaborativos.gamehub.payload.response;

import com.bytescolaborativos.gamehub.model.Role;
import com.bytescolaborativos.gamehub.model.Status;
import com.bytescolaborativos.gamehub.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private Role role;
    private Status rank;
    private Integer points;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.rank = user.getRank();
        this.points = user.getPoints();
    }
}
