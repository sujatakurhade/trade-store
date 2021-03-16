# Trade Store Spring Boot Application
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update expire flag if in a store the trade crosses the maturity date.

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

## To generate maven test report
```shell
mvn surefire-report:report
```  

## Code Coverage Report
```shell
/target/site/jacoco/index.html
```
## Maven Test Report
```shell
/target/site/surefire-report.html
```
## Trade store app has following RESTFUL API endpoints

### 1. Store the trade
**Description:** Create Trade

**Method:** POST

**URL:** [http://localhost:8181/trade](http://localhost:8181/trade)

1. If trade is valid and created successfully.

**RequestBody:**  

    {
        "tradeId":"T1",       
        "version":1,   
        "counterPartyId":"CP-1",
        "bookId":"B1",
        "maturityDate":"2021-03-17",
        "expiredFlag":"N"	
    }

**ResponseBody:**
    true

2. If trade maturity date is invalid.

**RequestBody:**  
    
    {
        "tradeId":"T1",
        "version":1,
        "counterPartyId":"CP-1",
        "bookId":"B1",
        "maturityDate":"2021-03-14",
        "expiredFlag":"N"	
    }

**ResponseBody:**

    {
        "timestamp": "2021-03-16 03:16:21",
        "status": 406,
        "error": "NOT ACCEPTABLE",   
        "message": "Invalid Maturity date for trade T1,maturity date should be greater than today's date",      
        "path": "/trade"
    }
    
3. If trade trade version is less than existing version.

**RequestBody:**  

    {
        "tradeId":"T1",
        "version":0,
        "counterPartyId":"CP-1",
        "bookId":"B1",
        "maturityDate":"2021-03-14",
        "expiredFlag":"N"	
    }

**ResponseBody:**

    {
        "timestamp": "2021-03-16 03:20:44",      
        "status": 406,
        "error": "NOT ACCEPTABLE",
        "message": "Invalid version for trade T1,trade version should be greater or equal to existing version",
        "path": "/trade"
    }
