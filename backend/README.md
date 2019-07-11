# baseProject (electronic Pay Per Reportable)

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
* You can find the Web API documentation in JSON format at `[scheme]://[host]:8090/v2/api-docs`.
* You can find a simple client (also the documentation) at `[scheme]://[host]:8090/swagger-ui.html`

### Continuous integration

http://jenkins.kiosk.eolivenza.com/job/Projects/job/eolivenza.DE.LIS/job/infinity-baseProject/

### Test annotation
In order to synchronize the IT to HPALM we can add tags to our tests. 
Those tags can be added at Class level, and then will apply to all the tests methods of the class.

See README at https://stash.intranet.eolivenza.com/stash/projects/RDA/repos/test-definition-annotations/browse

Sample of all tags in a test method:
```java
  @Test
  @ALM(internalID = "5465644-65464-4554", requirements = {"req-1", "req-2"}, nics={"nic-1", "nic-2"})
  @Creation(owner = "kimJong", date = "2017-10-22")
  @Approval(user = "nMaduro")
  @Review(user = "dTrump", date = "2018-10-03")
  @Definition(name="test2",
          verify = {
            @Verification(when = "Every time I grow up", then = "I fall")
          }
  )
  public void test3() {
      assertTrue(true);
    }
```

Or we can put some tags at class level and the rest at method level:
Sample:
```java
@Creation(owner = "kimJong", date = "2017-10-22")
@Review(user = "dTrump", date = "2018-10-03")
@Approval(user = "nMaduro")
@Narrative(description = "As a user I want to break free")
public class DummyTest {

 @Test
  @ALM(internalID = "5465644-65464-4554", requirements = {"req-1", "req-2"}, nics={"nic-1", "nic-2"})
  @Definition(name="test1", given = "I'm the best", verify = {@Verification(when = "Every time I grow up", then = "I fall")})
  public void test1() {
    assertTrue(true);
  }
```
  
## Contributing

### Branch strategy 
 ![branch strategy](/docs/branching-strategy/branching-strategy-img.png)

####Branches:
 There will be two types of branches: 
 * **Task branch**: Created from PBI branch. It will contain all commits needed to finish a task. Once all the features, UTs and ITs 
  related to the task are finished, the branch will be reintegrated into its parent PBI through a pull request.
 * **PBI branch**: Created from master branch. It will contain all the different tasks from a given PBI and once all 
 the tasks related to this PBI are finished and merged into PBI branch, it will be merged into master. 

##### Rules:
 1. A task branch can only be merged into a PBI branch after a pull request
 1. PBI branches will only be merged into master once all tasks are finished (including functional reviews, acceptance tests and development tasks)
 1. Once a pull request is approved and completed, the source branch must be deleted 
 1. Be aware of remote branches changes (i.e. often do fetch and merge if necessary as well as to review often the status of the ancestors branches)
 
##### Best scenario
In order to minimize the synchronization overhead between branches, each PBI branch should be smaller enough to avoid
the creation of task branches.
 
###Step by step
1. Create a 'feature/PBI/my-new-task' branch from master in TFS. Example: `feature/PBI12345/AddInstrumentTobaseProject`
1. Commit your changes to the task branch: `git commit -am 'Add some feature'`
1. Push to the task branch: `git push origin feature/PBI/my-new-task`
1. Submit a pull request from task branch to 'master' branch in TFS and add a reviewer :D

## Ubiquitous language

* **Analyzer code number**: 
* **Result**:
* **Reagent consumer**:
* **Instrument**:
* **Module**:
* **Activity**:
* **Configuration**: