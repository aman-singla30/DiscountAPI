FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=target/DiscountAPI-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]