FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar /target/scrapper.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/target/scrapper.jar"]