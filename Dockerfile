FROM java:8-jdk-alpine
COPY ./build/libs/CountryNeighboursTour-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar","CountryNeighboursTour-0.0.1-SNAPSHOT.jar"]