# US 015 - List booking requests

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...      | Answer                        | Justification (with patterns)                                                                                 |
| :------------- | :----------------------------------------------- | :---------------------------- | :------------------------------------------------------------------------------------------------------------ |
| Step 1         | ... interacting with the actor?                  | ListBookingRequestsUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                         | ListBookingRequestsController | Controller                                                                                                    |
|                | ... knowing the user using the system?           | UserSession                   | IE: cf. A&A component documentation.                                                                          |
| Step 2         | ... asking the required owner data?              | ListBookingRequestsUI         | interacts with the actor                                                                                      |
|                | ... answering the prompt to accept or reject     | User (actor)                  | knows the information                                                                                         |
| Step 4         | ... getting advertisement list?                  | AdvertisementRepository       | Information Expert - Repository that creates and saves a list of advertisements that are created              |
|                | ... answering the prompt to select one           | User (actor)                  | knows the information                                                                                         |
| Step 5         | ... getting visits list?                         | Advertisement                 | Information Expert - Advertisement is responsible for keeping info on visit request to itself                 |
|                | ... answering the prompt to select one           | User (actor)                  | knows the information                                                                                         |
| Step 6         | ... asking the required owner data?              | ListBookingRequestsUI         | interacts with the actor                                                                                      |
|                | ... choosing whether to accept or reject it      | User (actor)                  | knows the information                                                                                         |
| Step 7         | ...changing visit status?                        | Advertisement                 | Information Expert - Advertisement is responsible for keeping info on visit request to itself                 |
| Step 8         | ... asking whether the agent wishes to continue? | ListBookingRequestsUI         | interacts with the actor                                                                                      |
|                | ... answering the prompt                         | User (actor)                  | knows the information                                                                                         |


### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

- Advertisement
- Visit
- Person
- PersonRepository
- AdvertisementsRepository

Other software classes (i.e. Pure Fabrication) identified:

- ListBookingRequestsUI
- ListBookingRequestsController

## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us015-sequence-diagram-full.svg)

### Alternative 2 - Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.
![Sequence Diagram - split](svg/us015-sequence-diagram-split.svg)

**Get Preliminary Info**

![Sequence Diagram - Partial - Get Preliminary Info](svg/us015-sequence-diagram-partial-get-preliminary-info.svg)

**Get Advertisement List**

![Sequence Diagram - Partial - Get Task Category Object](svg/us015-sequence-diagram-partial-get-advertisement-list.svg)

**Get Visit List**

![Sequence Diagram - Partial - Get Employee](svgus015-sequence-diagram-partial-get-visit-list.svg)

**Change Visit Status**

![Sequence Diagram Partial - Change Visit Status](svg/us015-sequence-diagram-partial-change-visit-status.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us015-class-diagram.svg)
