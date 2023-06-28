FROM openjdk:11
ADD target/odpea-0.0.1-SNAPSHOT.jar /opt/odpea/odpea-0.0.1-SNAPSHOT.jar
WORKDIR /opt/odpea
ENV TZ Europe/Moscow
ENTRYPOINT ["java", "-Xmx4g", "-jar", "/opt/odpea/odpea-0.0.1-SNAPSHOT.jar"]
