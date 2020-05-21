FROM openjdk:11
ADD atlantic-union-bank-documentation-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9093
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]