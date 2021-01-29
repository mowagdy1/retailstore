# RetailStore

Yes, it's my first project using Java & Spring Boot. This side project is part of a store's backend.
It contains three endpoints; user creation, bill creation, and bill retrieving.
This bill may have a discount. It depends on predefined rules based on user type and other conditions.

I built this code with the clean architecture and wrote well-defined unit tests. Also, I used a command and a factory pattern.

### Prerequisites

* You need to have java installed on your system. You can get the Java from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

* You also need to have Postman installed if you want to test the endpoints yourself. Just download it from [here](https://www.postman.com/downloads/).

* What if you would like to run the unit tests? You can use your terminal or an IDE. For me, when it comes to IDE, I go with [IntelliJ IDEA](https://www.jetbrains.com/idea/download/). 

### Running

After installing Java and cloning the project, You can run it with:
```
./mvnw spring-boot:run
```

And for running the unit tests:
```
./mvnw test
```

For Windows users, change "./mvnw" to ".\mvnw".

## Built With

* [Java](http://www.java.com) -> Programming Language
* [Spring Boot](https://spring.io/projects/spring-boot) -> The Backend Framework
* [Maven](https://maven.apache.org/) -> Dependency Management
* [H2](https://www.h2database.com/) -> In-Memory Relational Database
* [Mockito](https://site.mockito.org/) -> Mocking Framework For Unit Tests
* [Hibernate](https://hibernate.org/orm/) -> ORM


## Author

* [**Mohamed Wagdy**](https://github.com/MoWagdy1)
