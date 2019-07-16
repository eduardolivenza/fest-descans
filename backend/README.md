# baseProject 

Project goal goes here.

## Table of Contents
1. [Getting Started](#getting-started)  
1. [Contributing](#contributing)
1. [Ubiquitous language](#ubiquitous-language)

##Getting Started

### Prerequisites
* **Git**: Recommended to have Git on Windows (http://git-scm.com/download/win)
* **Java Development Kit**: https://drive.google.com/open?id=1vKeS7olEr9nmoA3m31gmEhNoa8kztyI5
* **Maven**: https://maven.apache.org/download.cgi

**Environment configuration document:**
 https://docs.google.com/document/d/1DQOgXCFFceTuGrLeBTaCPYPAzfud5XUpk5RbGGiuZ4g/edit#


### Spring profiles

#### Profiles summary:

| Profile        | Main purposes | Physical database used | Automatic actions performed on baseProject domain tables during bootstrap | Automatic actions performed on INFINITY tables during bootstrap |
| ------------- |---|-------------|-----|---|
| development      | Development, functional reviews | Infinity instance | none | none |
| debug      |Product environment with debug traces | Infinity instance | none| none |
| prod      | Production environment| Infinity instance |   none | none |
| controller-to-secondary-adapters-with-in-memory-h2      | backend automatic testing with a fake database | in-memory H2      | drops and creates empty tables | drops and creates empty tables |

In order to activate profiles you have to use the option `-Dspring.profiles.active=` or you have to pass the argument `--spring.profiles.active=`.

#### Development
Intended for run the application in a development (maybe testing) environment. **This profile drops and creates the tables with no data on each run**.

1. Be sure that an Infinity instance is running in your environment
2. In the command-line run the baseProject (e.g. `baseProject-runtime-0.0.1-SNAPSHOT.jar`) with the development profile:
    
    Defining application arguments (`--`):`java -jar baseProject-runtime-0.0.1-SNAPSHOT.jar --spring.profiles.active=development`
    
    or
    
    Defining system properties (`-D`): `java -Dspring.profiles.active=development -jar baseProject-runtime-0.0.1-SNAPSHOT.jar`

#### Production
Intended for run the application in a production environment. **This profile has no database initialization task**.

1. Be sure that an Infinity instance is running in your environment
2. In the command-line run the baseProject (e.g. `baseProject-runtime-0.0.1-SNAPSHOT.jar`) with the production profile:
    
    Defining application arguments (`--`):`java -jar baseProject-runtime-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`
    
    or
    
    Defining system properties (`-D`): `java -Dspring.profiles.active=prod -jar baseProject-runtime-0.0.1-SNAPSHOT.jar`
    
### Other spring profiles

#### controller-to-secondary-adapters-with-in-memory-h2
Intended for backend automatic testing. **This profile uses a fake in-memory database (H2)**. Typically used during maven lifecycle.


### Run tests
1. Go to baseProject pom.xml directory at the repository root
2. Execute `mvn clean verify` (http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

### Deployment
Application is deployed at `[scheme]://[host]:8090`

Typical example in a development environment: `http://localhost:8090`
### Web API
* You can find the Web API documentation in JSON format at `[scheme]://[host]:9191/v2/api-docs`.
* You can find a simple client (also the documentation) at `[scheme]://[host]:91917swagger-ui.html`



