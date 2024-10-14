# Contact Store #

This is a sample project using Spring Boot with a small set of REST APIs for demo purpose.

## Note for developers

### Prerequisites

* Install Docker & Docker Compose or Docker Desktop

### IDE
To run springboot app in the IDE, 
- Add following to program arguments  
  ```
  SPRING_DATASOURCE_URL=contact_store
  SPRING_DATASOURCE_USERNAME=contact_store 
  SPRING_DATASOURCE_PASSWORD=contact_store
  ```
  
### Command line
To run springboot app from the commandline

> `SPRING_DATASOURCE_URL=contact_store SPRING_DATASOURCE_USERNAME=contact_store SPRING_DATASOURCE_PASSWORD=contact_store ./gradlew bootRun`

### Tests
Before committing code run both unit and functional tests:

> `./gradlew clean check`

During development, you can run just unit tests:

> `./gradlew test`

### Annex

If you like to run the PostgresSQL as standalone for development see the `db_init.sh` file in scripts.