# Auth-Service (Microservicio de Usuarios)

Este directorio contiene un **microservicio** desarrollado con **Spring Boot** y **MongoDB** para la segunda parte de la prueba técnica. Ofrece operaciones CRUD sobre usuarios, validaciones, seguridad con JWT y encriptación de contraseñas. Puede ejecutarse **localmente** o mediante **Docker Compose** junto a MongoDB. Incluye soporte para **Swagger**, facilitando la documentación y prueba de los endpoints.

---

## 1. Requisitos

- **Java 21**
- **Gradle** si deseas compilar localmente sin Docker.
- **Docker** y **Docker Compose** para levantar el microservicio y MongoDB en contenedores.

---

## 2. Estructura de Archivos

- **src/**  
  Contiene el código fuente (controladores, servicios, repositorios, pruebas, etc.).
- **Dockerfile**  
  Define cómo se construye la imagen de Docker para este microservicio.
- **docker-compose.yml**  
  Permite levantar **MongoDB** y **Auth-Service** en contenedores, usando persistencia en el volumen `mongo_data`.
- **application.properties**  
  Configuración de Spring Boot (ruta de contexto `/xoftix`, placeholders para Mongo, JWT, etc.).
- **build.gradle (o pom.xml)**  
  Script de Gradle (o Maven) para compilar y manejar dependencias.

---

## 3. Ejecución Local (sin Docker)

1. **Clona** o descarga este repositorio y ubícate en la carpeta `test2/auth-service`.
2. Asegúrate de tener **MongoDB** corriendo localmente en `localhost:27017` (por defecto, la app usa `admin/admin` como usuario/contraseña).
3. **Compila** el proyecto:

    - En Linux/Mac:
      ```bash
      ./gradlew clean bootJar
      ```
    - En Windows:
      ```bash
      gradlew.bat clean bootJar
      ```

4. **Ejecuta** el `.jar` generado:
   ```bash
   java -jar build/libs/auth-service.jar
   ```

---

## 4. Ejecución con Docker

1. **Construye y levanta los contenedores**
   ```bash
   docker compose up --build
   ```
2. El microservicio y la base de datos estarán ejecutándose en sus respectivos contenedores.
3. Puedes usar **Postman** o cualquier otra herramienta para probar los endpoints.

---

## 5. Ver Endpoints con Swagger

1. **Accede a la documentación Swagger** en tu navegador:
   ```
   http://localhost:8090/xoftix/swagger-ui/index.html
   ```
2. Desde ahí podrás probar los endpoints de forma interactiva.

---

## 6. Pruebas Unitarias

1. **Ejecuta las pruebas unitarias con Gradle**:
   ```bash
   ./gradlew test
   ```
   o en Windows:
   ```bash
   gradlew.bat test
   ```
2. Se ejecutarán todas las pruebas definidas en el proyecto.

