# **🎮 GameHub - Backend para Torneos de Videojuegos Online**

**GameHub** es una API REST construida con Spring Boot para gestionar torneos de videojuegos en línea. Este proyecto incluye funciones como emparejamiento automático de jugadores, gestión de usuarios por roles, chat básico por torneo/partida y visualización de clasificaciones en tiempo real.

---

## **🚀 Funcionalidades Principales**

- Registro e inicio de sesión de usuarios con JWT.
- Roles: `ADMIN` y `PLAYER`.
- Creación, listado y registro en torneos.
- Emparejamiento automático de jugadores por ronda.
- Chat básico (polling HTTP) en torneos y partidas.
- Seguimiento de resultados y tabla de clasificación.

---

## **🛠️ Tecnologías Utilizadas**

- **Lenguaje:** Java 17
- **Framework:** Spring Boot (MVC)
- **Seguridad:** Spring Security + JWT
- **Base de datos:** PostgreSQL
- **Documentación de API:** Swagger UI
- **Testing:** Postman | JUnit | Mockito
- **Gestión de proyecto:** Trello
- **Control de versiones:** Git & GitHub

---

## **🧱 Arquitectura del proyecto**

El proyecto sigue una estructura organizada por capas:

Controladores: Manejan la lógica de las peticiones HTTP. Servicios: Contienen la lógica de negocio. Repositorios: Administran la persistencia de datos.

**📂 Estructura de carpetas**

```
gamehub/
├── src/
│   ├── main/
│   │   ├── java
│   │   │   └── gamehub/
|   |   |       ├── config/
│   │   │       ├── controller/
|   |   |       ├── dto/
|   |   |       ├── model/
│   │   │       ├── repository/
|   |   |       ├── security/
│   │   │       └── service/
│   │   └── resources/
│   └── test/
├── README.md
├── .env.demo
├── .gitignore
└── pom.xml

```

---

## **📦 Instalación**

1. Clona este repositorio:
    
    ```
    git clone https://github.com/puriihuaman/game-hub-bc.git
    ```
    
2. Configura tu base de datos PostgreSQL y agrega los valores en application.properties o application.yml.
    
    ```
     spring.datasource.driver-class-name=org.postgresql.Driver
     spring.datasource.url=${SPRING_DATASOURCE_URL}
     spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
     spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
    ```
    
3. Ejecuta el proyecto desde tu IDE o consola:
    
    ```
    ./mvnw spring-boot:run
    ```
    
4. Accede a la documentación de la API:
    
    ```
    http://localhost:8080/swagger-ui/index.html
    
    ```
    

---

## **🔐 Autenticación**

Se utiliza JWT para la autenticación. Los únicos endpoints públicos son:

- **POST** /api/auth/register
- **POST** /api/auth/login

El token JWT se debe enviar en el header de las peticiones protegidas:

```
Authorization: Bearer <token>

```

## **🧑‍💻 Endpoints Principales**

**🧾 Autenticación**

- **POST** /api/auth/register: Registro de usuario (PLAYER por defecto).
- **POST** /api/auth/login: Login y obtención de JWT.

**👥 Usuarios**

- **GET** /api/users/me: Ver perfil del usuario autenticado.
- **GET** /api/users/{id}: Ver perfil público de cualquier usuario.

**🏆 Torneos**

- **POST** /api/tournaments: Crear torneo (solo ADMIN).
- **GET** /api/tournaments: Listar torneos disponibles.
- **GET** /api/tournaments/{id}: Ver detalles de torneo.

**POST** /api/tournaments/{id}/join: Unirse a un torneo (solo PLAYER).

**🎮 Partidas y Emparejamientos**

- **POST** /api/matches/generate/{tournamentId}: Generar emparejamientos (solo ADMIN).
- **PUT** /api/matches/{id}/result: Actualizar resultado (solo ADMIN).
- **GET** /api/matches/{id}: Ver detalles de una partida.

**💬 Chat (HTTP Polling)**

- **GET** /api/tournaments/{id}/messages
- **POST** /api/tournaments/{id}/messages
- **GET** /api/matches/{id}/messages
- **POST** /api/matches/{id}/messages

**📊 Clasificación**

- **GET** /api/tournaments/{id}/ranking: Ver tabla de posiciones del torneo.

**📌 Modelo de Datos**

- Entidad `user`:

| **Campo** | **Tipo** | **Descripción** |
| --- | --- | --- |
| `id` | `UUID` | Identificador único |
| `username` | `String` | Nombre de usuario |
| `email` | `String` | Email del usuario |
| `role` | `Enum (RoleType)` | Descripción del rol |
| `rank` | `Status(RoleType)` | Posicion del ranking |
| `points` | `Integer` | Puntos obtenidos |
- Entidad `Tournament` :

| **Campo** | **Tipo** | **Descripción** |
| --- | --- | --- |
| `id` | `UUID` | Identificador único |
| `name` | `String` | Nombre del torneo |
| `maxPlayers` | `int` | Número máximo de jugadores |
| `status` | `Status (RoleType)` | Estado |
| `players` | `List` | Lista de jugadores |
- Entidad `Match`:

| **Campo** | **Tipo** | **Descripción** |
| --- | --- | --- |
| `id` | `UUID` | Identificador único |
| `tournamentId` | `UUID` | Identificador único |
| `player1` | `User` | Jugador 1 |
| `player2` | `User` | Jugador 2 |
| `result` | `Result` | Descripción del rol |
| `round` | `int` | Numero de round |
- Entidad `Message`:

| **Campo** | **Tipo** | **Descripción** |
| --- | --- | --- |
| `id` | `UUID` | Identificador único |
| `senderId` | `UUID` | Identificador único |
| `content` | `String` | Contenido del mensaje |
| `timestamp` | `LocalDataTime` | Hora |
| `matchId` | `UUID` | Identificador único |
| `tournamentId` | `UUID` | Identificador único |

## **📣 Créditos**

Proyecto desarrollado como parte de la formación en backend organizada por:

- @programando_con_jorge
- @__devch
