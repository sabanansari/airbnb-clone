# airBnbApp — README

Purpose
- Quick start and developer orientation for this Spring Boot (Java 21) monolith.

High-level plan for this change
- Create a concise README.md in project root with build/run instructions (Windows), key patterns, and important files to read next.

Quick start (Windows PowerShell)
- Build the project:
```powershell
.\mvnw.cmd clean package
```
- Run tests:
```powershell
.\mvnw.cmd test
```
- Run from Maven (dev):
```powershell
.\mvnw.cmd spring-boot:run
```
- Run the packaged jar:
```powershell
java -jar target\airBnbApp-0.0.1-SNAPSHOT.jar
```
- Run with runtime property overrides (example):
```powershell
java -jar target\airBnbApp-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/airBnb --spring.datasource.username=postgres --spring.datasource.password=YOUR_DB_PW --jwt.secretKey=YOUR_JWT_SECRET
```
- Set an environment variable (PowerShell) for a single shell session:
```powershell
$env:SPRING_DATASOURCE_URL = 'jdbc:postgresql://localhost:5432/airBnb'
$env:SPRING_DATASOURCE_USERNAME = 'postgres'
$env:SPRING_DATASOURCE_PASSWORD = 'YOUR_DB_PW'
.\mvnw.cmd spring-boot:run
```

Important runtime notes
- All HTTP endpoints are served under the context path `/api/v1` (see `src/main/resources/application.properties`).
- OpenAPI / Swagger UI (springdoc):
  - Swagger UI: `/api/v1/swagger-ui/index.html`
  - OpenAPI JSON: `/api/v1/v3/api-docs`
- The project uses PostgreSQL. Default connection settings are in `src/main/resources/application.properties` (do not commit secrets in real projects).
- Stripe and JWT secrets are configured in `application.properties` in this workspace; prefer using environment variables or an externalized config for local development.

Project structure & patterns (what to read first)
- Entry point: `src/main/java/com/ansari/projects/airBnbApp/AirBnbAppApplication.java`
- Global response & error handling:
  - `src/main/java/com/ansari/projects/airBnbApp/advice/ApiResponse.java`
  - `src/main/java/com/ansari/projects/airBnbApp/advice/GlobalResponseHandler.java`
  - `src/main/java/com/ansari/projects/airBnbApp/advice/GlobalExceptionHandler.java`
  - NOTE: Controllers should return DTOs or domain objects and let `GlobalResponseHandler` wrap responses — avoid double-wrapping with `ApiResponse` manually unless required by tests.
- Security / Auth:
  - `src/main/java/com/ansari/projects/airBnbApp/security/JWTService.java` (JWT creation/validation)
  - `src/main/java/com/ansari/projects/airBnbApp/controller/AuthController.java`
  - `src/main/java/com/ansari/projects/airBnbApp/entity/User.java` (user roles as enum collection)
- Services & data flow:
  - Services follow the `*Service` interface + `*ServiceImpl` pattern (`service/*Impl.java`).
  - DTOs live under `dto/` and entities under `entity/`.
  - Booking flow: `BookingServiceImpl` interacts with `Inventory` and `Booking` entities — look there for timing/expiration logic.
- Configs:
  - `src/main/resources/application.properties` — DB, JWT, Stripe, context path.
  - `src/main/java/com/ansari/projects/airBnbApp/config/MapperConfig.java` — ModelMapper bean used across services.

Conventions to follow when contributing
- Follow existing Java style: Lombok annotations (`@RequiredArgsConstructor`, `@Builder`), `@Slf4j` for logging.
- Service pattern: create an interface and an `*ServiceImpl` implementation.
- Return DTOs from controllers; mapping via `ModelMapper` is preferred.
- Use provided custom exceptions (`ResourceNotFoundException`, `UnauthorizedException`, etc.) to trigger consistent error responses.
- Keep controller methods lean: delegate business logic to service layer.

Local development tips
- SQL logging is enabled (`spring.jpa.show-sql=true`) — useful when debugging JPA queries.
- If you need to change DB schema behavior for local experiments, edit `spring.jpa.hibernate.ddl-auto` in `application.properties` (default here: `update`).
- To avoid committing secrets, add a local override file (e.g., `application-local.properties`) and add it to `.gitignore`.

Key files (quick index)
- `pom.xml` — dependencies (Spring Boot, Spring Security, JJWT, Stripe, springdoc-openapi, ModelMapper)
- `src/main/resources/application.properties`
- `src/main/java/com/ansari/projects/airBnbApp/AirBnbAppApplication.java`
- `src/main/java/com/ansari/projects/airBnbApp/advice/GlobalResponseHandler.java`
- `src/main/java/com/ansari/projects/airBnbApp/advice/GlobalExceptionHandler.java`
- `src/main/java/com/ansari/projects/airBnbApp/security/JWTService.java`
- `src/main/java/com/ansari/projects/airBnbApp/service/BookingServiceImpl.java`

Where to go next
- Read the `advice` package first to understand how responses/exceptions are shaped.
- Inspect `AuthController` and `JWTService` to understand token flows.
- Run the app and open the Swagger UI to explore endpoints interactively.

Security & responsible disclosure
- Secrets (JWT secret, Stripe keys, DB passwords) should never be committed to public repos. They are present in the local `application.properties` for convenience in this workspace — rotate them if this repository is public.

Contact / Maintainers
- Project author package: `com.ansari.projects.airBnbApp` — look in Git history or project docs for maintainer contact.
