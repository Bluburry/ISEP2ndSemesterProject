# US 009 - Client Schedule Visit  

## 1. Requirements Engineering


### 1.1. User Story Description


As a client, I want to leave a message to the agent to schedule a visit to a property of my interest.

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

All registered information, except the agency commission, can be accessed by the client who intends to buy or rent the property; the client is, then, responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the city where the property is located.

A list of available properties must be shown, sorted from the most recent entries to the oldest.

The message must also include the client's name, phone number,  preferred date and time slot (from x hour to y hour) for the property visit.

A client may post multiple visit requests, but only if those do not overlap  each other.

The client must receive a success message when the request is valid and registered in the system

**From the client clarifications:**

> **Question:** Quote from the project description (2.1 - 4th paragraph): "The agent receives the request, checks the availability and sends the response. If the customer accepts the order, it is automatically scheduled in the system." Is the above sentence correct or did you mean "If the agent accepts the visit, it is automatically scheduled in the system." ?
>  
> **Answer:** Thank you! If the agent accepts the visit, it is automatically scheduled in the system.

> **Question:** Are the visits done to a property only made by the agent responsible for it?
>
> **Answer:** Yes.

### 1.3. Acceptance Criteria


* **AC1:** The Client can access all the information in a property except the agency commission.
* **AC2:** The Client has to provide the client's name, phone number, preferred date and time slot (from x hour to hour) for the property visit.
* **AC3:** A client may post multiple visit requests, but only if those do not overlap  each other.
* **AC4:** The request must be valid and registered in the system.


### 1.4. Found out Dependencies

* There is a dependency to US3: "As a system administrator, I want to register a new employee. " since the Agent has to be a registered employee by the Systems Administrator.
* There is a dependency to US4: "Owner Submit A Request For Listing A Property." since The request must be valid and registered in the system.
* There is a dependency to US2: "Publish Sale Announcement." since The request must be valid and registered in the system.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * client's name
  * phone number
  * preferred date and time slot for the property visit.

* Selected data:
	* Advertisement List to choose one.

**Output Data:**

* Message:
  * Client must receive a success message when the request is valid and registered in the system

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

![System Sequence Diagram](svg\us009-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* n/a