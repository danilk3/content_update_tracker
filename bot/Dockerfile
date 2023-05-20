FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar /target/bot.jar
EXPOSE 8080 8082
ENTRYPOINT ["java","-jar","/target/bot.jar"]
