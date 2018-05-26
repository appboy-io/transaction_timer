# transaction_timer
Small sping boot app that takes transactions committed every 60 seconds, and reports on the latest transactions

## Running this code
This project is built using maven, simply run
```
mvn spring-boot:run
```
The application will run on localhost:8080

## Embedded Database
This project runs an embedded mongodb database on port 3306. If you need to change this port for any reason, you can change it here
```
src/main/java/com/transaction/n26/n/config/MongoConfig.java
```

## Running Test
This projects test can be ran using 
```
mvn install
```
