FROM openjdk:8
ADD target/drogaria-1.0.jar drogaria.jar
ENTRYPOINT ["java", "-jar", "drogaria-1.0.jar"]

