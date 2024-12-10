# US 016 - Agent respond to a booking request for a visit.

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

After consulting a list of properties, the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions. 

The agent receives the request, checks the availability and sends the response. 

If the customer accepts the order, it is automatically scheduled  in the system.

**From the client clarifications:**

> **Question:** Will it be necessary : save the message for could be in future list in the application or for audit action; OR isn´t necessary save. Is only to send and the complete message (email) will not be auditable not even possible consult in the application in the future?
>  
> **Answer:** The system should record typed text messages. Regarding other messages, that only use information that is already registered in the system, the system should only record that the message was sent.

> **Question:** Our team is having trouble understanding US016's AC2. Until now, the email has been sent in the form of a text file, however, with this AC, a configuration file that allows the use of different platforms has been introduced. How should the sending of emails be carried out then?
>
> **Answer:** The configuration file defines the email service to be used. The URI of the email service should be defined in the configuration file. The URI can be the path of a file. .

> **Question:** When the agent requests the booking requests list to contact the client, that list should ONLY contain the requests related to that agent?
>
> **Answer:** Yes. Listing is a feature described in US15. Important: In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer any booking request.

> **Question:** When the agent is responding to the user that created the request, what should the answer be? Because accepting or declining the request is already done in US011.
>
> **Answer:**  In US11 the agent wants to accept or decline a purchase order for a property. In US16 the agent wants to answer visit requests.

> **Question:** In AC2, what is DEI's email service? Are you referring to Outlook?
>
> **Answer:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.). DEI email service is an email service like gmail or Outllook. These are only examples and you should prepare your system to support any email service.

### 1.3. Acceptance Criteria

* **AC1:** An Agent is registered employee by the Systems Administrator. 
* **AC2:** The booking request must be of a property managed by the Agent.
* **AC3:** The Client must be a registered user to Request a Booking.
* **AC4:** The request must be valid and registered in the system.
* **AC5:** The response is sent by email.
* **AC6:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.).
* **AC7:** The response should include the name and phone number of the responsible Agent.
* **AC8:** The response should include the property identification and location. 

### 1.4. Found out Dependencies

* There is a dependency to US03: "As a system administrator, I want to register a new employee. " since the Agent has to be a registered employee by the Systems Administrator.
* There is a dependency to US07: "Register in the system " The Client must be a registered user.
* There is a dependency to US15: "List agent managed properties." The Agent responds only to request booking of the property he manages.
* There is a dependency to US09: "Client Schedule Visit." Client request a Visit

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * Reject or Accept Booking.
	
* Selected data:
  * List of Booking Request
  * List of different platforms of Email

**Output Data:**

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

![System Sequence Diagram](svg\us016-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* Agent must Log In to Accept a Booking Request.