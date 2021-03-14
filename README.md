# Trade Store Spring boot Application

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.trade.store.StoreApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## REST API Endpoint:
### 1. Store the trade
URL: http://localhost:8181/trade
Method: POST
Request Body: 
`{
	"tradeId":"T3",
	"version":4,
	"counterPartyId":"CP-3",
	"bookId":"B3",
	"maturityDate":"2021-03-14",
	"expiredFlag":"N"	
}`

Response Body:
True

{
    "error": "Invalid Maturity date for trade T3"
}

{
    "error": "Invalid version for trade T3"
}