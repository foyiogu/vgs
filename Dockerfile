FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/vgs-recruitment.jar vgs-recruitment.jar
ENTRYPOINT ["java", "-jar", "vgs-recruitment.jar"]

