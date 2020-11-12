# Apply Discount on the bill amount API
### This project is about the bill discount

## Prerequisite
    - Install Java 11 Openjdk
    - Insatll Docker 
    - use Intellij
    - Install maven

## Steps to run the application:
    You can run the application in standalone mode by using:
    - mvn clean install
    - mvn spring-boot:run
    
     Dockerized mode :
    - mvn clean install
    - docker build .
    - docker run --publish 8000:8080 --detach --name bb <image name>
    
##Steps to generate the SONAR report :
    -mvn clean install
    -mvn sonar:sonar
    
You can find class diagram for repository,controller and service at the root path


Api is available at below url :
http://localhost:8080/swagger-ui.html