FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN chown -R guest:users /app
USER guest

COPY product-service/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
