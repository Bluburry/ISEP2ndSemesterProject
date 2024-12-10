# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely:Audit, Reporting and Security._
- All users besides the unregistered one should log into the application for operations to be executed.
- An unregistered user can only sort and display listed properties.
- Specific roles have restricted functionalities.

## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- Error prevention: 
  - Whenever an error occurs in the data typed by the user the system requires the insertion of new data.
- Interface aesthetics and design:
- Help and documentation: 
  - Anything that is not understood by the user, is explained in the project documentation (glossary).
- Consistency and standards:

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

- Frequency and severity of failure:
- Possibility of recovery: 
- Possibility of prediction: 
- Accuracy:
- Average time between failures:

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

- Response time: 
- Start-up time: 
- Recovery time:
- Memory consumption:
- CPU usage: 
- Load capacity:
- Application availability: 
  - The application is designed so that it can be used at any time by an interested user.

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._ 

- Testability: 
  - JUnit 5 framework should be used to implement tests for all methods (except I/O operations) and test coverage report must be generated.
- Adaptability: 
  - The fact that we are using maven structure because we can run it in many devices.
- Maintainability: 
- Compatibility: 
  - Compatibility with an old application.
- Configurability:
- Instability:
- Scalability:


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- Programming languages:
  - Java may be used.
- Software process: 
- Mandatory standards/patterns: 
  - Must adopt recognized coding conventions and standards (e.g., CamelCase).
- Use of development tools:
  - Application graphical interface is to be developed in JavaFX 11.
- Class library:


### Implementation Constraints

_Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- 3rd party components: 
    
- Implementation languages: 
  - Java may be used.
- Platform support:
  - Windows/MacOS/UNIX and LINUX based systems/Android/iOS.
- Resource limits:
- Standards-compliance: 
  - Must adopt recognized coding conventions like GRASP.
- Mandatory standards/patterns:
  - Password of seven alphanumeric characters, including three capital letters and two digits
- Database Integrity: 
  - The application should use object serialization to ensure data persistence between two runs of the application.

### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

- External systems:
- Interface formats:
    - All images/figures must be created in a SVG format.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

- Shape:
- Size:
- Weight: