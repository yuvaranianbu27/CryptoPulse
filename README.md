# CryptoPulse

**A Java and Spring Boot backend prototype for a crypto application.**

The repository currently focuses on account and authentication scaffolding. It contains user and two-factor-authentication domain models, registration and authentication request DTOs, JWT-related classes, and `/signup` and `/signin` route declarations.

> **Project status:** This is a work in progress. The authentication flows are not complete or verified as production-ready. No market-data, wallet, or trade-order API implementation is present in the current source tree.

## Current scope

- User registration and sign-in controller scaffolding.
- User roles and a two-factor-authentication domain model.
- Spring Security, password-encoding, and JWT-related components.
- MySQL/JPA persistence setup.

## Technology

| Technology | Use |
| --- | --- |
| Java 21 | Application runtime |
| Spring Boot 3 | Application framework |
| Spring Security | Authentication and authorization foundation |
| Spring Data JPA | Persistence |
| MySQL | Configured relational database |
| JJWT | JWT-related implementation |
| Maven Wrapper | Build and test commands |

## Run locally

Prerequisites: Java 21 and MySQL. Configure database connection settings in `cryptopulse/src/main/resources/application.properties` for your local environment. Do not commit database passwords or signing keys.

From the repository root:

```powershell
.\cryptopulse\mvnw.cmd -f cryptopulse\pom.xml test
.\cryptopulse\mvnw.cmd -f cryptopulse\pom.xml spring-boot:run
```

On macOS or Linux, use `./cryptopulse/mvnw` instead of `.\cryptopulse\mvnw.cmd`.

## Before using beyond development

- Complete and test the registration and sign-in flows.
- Define and validate the JWT and two-factor-authentication lifecycle.
- Add API and persistence tests, and use externalized secrets and schema migrations.
- Implement market data, wallets, and order workflows if they are in the product scope.

## Repository layout

- `cryptopulse/src/main/java/.../controller/` — authentication route declarations.
- `cryptopulse/src/main/java/.../service/` — registration and user-detail services.
- `cryptopulse/src/main/java/.../Entity/` — user and two-factor-authentication models.
- `cryptopulse/src/main/resources/` — application configuration.
