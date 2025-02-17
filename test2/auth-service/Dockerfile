# Usamos una imagen base con OpenJDK 17
FROM openjdk:21

# Crea un directorio para tu aplicación
WORKDIR /app

# Copia el .jar de tu aplicación (generado con ./gradlew bootJar)
COPY build/libs/auth-0.0.1-SNAPSHOT.jar auth-service.jar

# Expone el puerto 8080 (donde corre tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
