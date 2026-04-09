# Build com Maven + JDK 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .

# Limpa cache e força atualização
RUN mvn dependency:purge-local-repository clean package -U -DskipTests

# Execução
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8289
ENTRYPOINT ["java", "-jar", "app.jar"]