FROM maven:3-jdk-11

COPY pom.xml /service/pom.xml
COPY src/ /service/src

WORKDIR /service/

RUN mvn package
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "target/authenticationmicroservice-1.0-SNAPSHOT.jar" ]
