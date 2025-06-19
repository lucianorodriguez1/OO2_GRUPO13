# Sistema de Gestión de Tickets - Programación Orientada a Objetos II (UNLa)

Este proyecto es una aplicación web construida con Java, Spring Boot y Thymeleaf que permite la gestión de tickets-  Incluye funcionalidades como ABM de soporte, cliente, tickets, validaciones, envío automático de correos, persistencia en base de datos y manejo de excepciones.

---

## ✅ Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
- MySQL
- Lombok
- Maven
- Jakarta Mail (envío de correos)

---

## 🚀 Pasos para levantar el proyecto

1. **Requisitos previos**
   - Java 17 o superior
   - Maven 3 o superior
   - MySQL instalado y corriendo
   - Plugin de Lombok instalado en tu IDE

2. **Clonar el repositorio**
   ```
   git clone https://github.com/lucianorodriguez1/OO2_GRUPO13
   ```
   
4. **Crear la base de datos y ejecutar los siguientes scripts para inicializar los roles**
   ```
   CREATE SCHEMA IF NOT EXISTS sistema_tickets;
   USE sistema_tickets;
   INSERT INTO usuario_rol (rol) VALUES ('USUARIO');
   INSERT INTO usuario_rol (rol) VALUES ('ADMIN');
   ```
5. **Configurar variables de entorno**
   - DB_URL (url de la base de datos)
   - USERNAME (nombre de usuario de la base de datos)
   - PASSWORD (contraseña de la base de datos)
   - EMAIL_SENDER
   - EMAIL_PASSWORD

   
   
   
