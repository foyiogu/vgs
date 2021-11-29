# Vgs Birthday API
This is a simple REST API for a simple birthday response.  The API shows Create, Update and Get operations for user


### Technologies
- Java
- Maven
- Springboot
- Swagger
- PostgreSQL
- Docker


### Requirements

You need the following to build and run the application:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.8.1](https://maven.apache.org) (Optional as code already contains maven wrapper)
- [PostgreSQL](https://www.postgresql.org/download)

You could also run it on docker. Hence, it would require you to install [docker](https://www.docker.com/products/docker-desktop)

## How to run (with docker)
### step 1 - clone project with from [here](https://github.com/foyiogu/vgs.git)

```
git clone https://github.com/foyiogu/vgs.git
```


### step 2 - move into the project directory
```
cd vgs/
```

### step 3 - Generate the .jar file
```
mvn clean install 
OR
./mvnw clean install
```

### step 4 - run the project on docker
```
docker-compose up
OR
docker compose up
```

For ease of use and to test the functionality, I have made documents of the endpoints here 

After running the Application, You can see the documentation on swagger and test the end points

### Full Swagger documentation can be found [here](http://localhost:8080/swagger-ui/)
