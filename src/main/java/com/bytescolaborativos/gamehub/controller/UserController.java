package com.bytescolaborativos.gamehub.controller;

import com.bytescolaborativos.gamehub.payload.response.MessageResponse;
import com.bytescolaborativos.gamehub.payload.response.UserResponse;
import com.bytescolaborativos.gamehub.model.User;
import com.bytescolaborativos.gamehub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

// Importaciones de Swagger/OpenAPI
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter; // Para documentar parámetros de ruta/consulta
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement; // Para indicar que el endpoint requiere seguridad

import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints para la gestión e información de usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @Operation(summary = "Obtener perfil del usuario autenticado",
            description = "Devuelve la información del perfil del usuario que está actualmente autenticado. Requiere un JWT válido.",
            security = @SecurityRequirement(name = "bearerAuth")) // Indica que este endpoint requiere JWT (definiremos 'bearerAuth' más adelante)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil del usuario devuelto exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error al obtener el usuario actual",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado / JWT inválido"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido (rol insuficiente)")
    })
    @GetMapping("/me") // Endpoint para obtener la información del usuario autenticado // Accesible para usuarios con rol PLAYER o ADMIN
    @PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')") // Requiere cualquiera de los dos roles
    public ResponseEntity<?> getAuthenticatedUser() {
        try {
            User currentUser = userService.getCurrentAuthenticatedUser();
            return ResponseEntity.ok(new UserResponse(currentUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error fetching current user: " + e.getMessage()));
        }
    }

    @Operation(summary = "Obtener perfil de un usuario por ID",
            description = "Devuelve la información del perfil de un usuario específico dado su ID. Requiere un JWT válido.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil del usuario devuelto exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "401", description = "No autorizado / JWT inválido")
    })
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(UserResponse::new) // Si se encuentra, mapea a UserResponse
                .map(ResponseEntity::ok) // Y devuelve 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra, devuelve 404 Not Found
    }
}
