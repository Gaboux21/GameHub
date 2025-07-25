package com.bytescolaborativos.gamehub.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Plataforma de Torneos de Videojuegos API",
                version = "1.0.0",
                description = "API REST para la organización y gestión de torneos de videojuegos online. Permite la creación y gestión de torneos, emparejamiento automático de jugadores, chat básico, clasificación y actualización de resultados.",
                contact = @Contact(name = "Tu Nombre", email = "tu.email@example.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor de Desarrollo Local")
        }
)
@SecurityScheme( // Define el esquema de seguridad global
        name = "bearerAuth", // Nombre que usaremos en @SecurityRequirement
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Se requiere un JWT (JSON Web Token) válido para acceder a esta ruta. Incluye el token en el encabezado Authorization como 'Bearer [token]'."
)
public class OpenApiConfig {

}
