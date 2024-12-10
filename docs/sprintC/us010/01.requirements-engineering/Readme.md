# US 010 - Client place an order to purchase the property

## 1. Requirements Engineering


### 1.1. User Story Description


As a client, I place an order to purchase the property, submitting the order amount.

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

When the client decides to buy/rent the property, he sends a request for the purchase/lease of the property to the agent.

The order amount submitted by the client must be equal to or lower than the price set by the owner for the property.

If the order amount submitted by the client has already been posted for the property (by another request from this client or any other client), the system must state that on the screen and the order placed previously should be considered first when selling the property.

A client can only submit a new order to purchase the same property after the previous one is declined.

**From the client clarifications:**

> **Question:** It was previously stated that an unregistered user could do a property listing request. However, with the introduction of US007, I want to clarify and make sure that now a user needs to be registered in order to buy, sell or rent properties, or if they can still do it unregistered.
>  
> **Answer:** In Sprint B we introduce US7 and now, in US4, the owner needs to be registered in the system to submit a request for listing. You should update all artifacts to include this change..

> **Question:** When showing the other order on the screen, what data should be shown (eg client name, published date, order status)?
> 
> > **Answer:** If the order amount submitted by the client has already been posted for the property (by another request from this client or any other client), the system must state that on the screen. The system should show the message "The order amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.".

> **Question:** To order a purchase of a property, should the client be able to filter the properties by type of property, city, district....so that it's easier to find the wanted property, or should the system show the entire list of properties to sale?
>
> > **Answer:** The system should show a list of properties to the client. Filtering is a useful feature of the system, please prepare a user friendly and effective filtering to show the properties to the client.

### 1.3. Acceptance Criteria

* **AC1:** The Client must be a registered user to buy, sell or rent properties (US07).
* **AC2:** The order amount submitted by the client must be equal to or lower than the price set by the owner for the property.
* **AC3:** If the order amount submitted by the client has already been posted for the property (by another request from this client or any other client), the system must state that on the screen and the order placed previously should be considered first when selling the property.
* **AC4:** A client can only submit a new order to purchase the same property after the previous one is declined.
* **AC5:** The request must be valid and registered in the system.
* **AC6:** An Agent is registered employee by the Systems Administrator.

### 1.4. Found out Dependencies

* There is a dependency to US3: "As a system administrator, I want to register a new employee. " since the Agent has to be a registered employee by the Systems Administrator.
* There is a dependency to US4: "Owner Submit A Request For Listing A Property." since The request must be valid and registered in the system.
* There is a dependency to US2: "Publish Sale Announcement." since The request must be valid and registered in the system.
* There is a dependency to US7: "Register in the system " The Client must be a registered user.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * amount in the order.
	
* Selected data:
  * Property Type
  * Business Type
  * Advertisement List to choose one.

**Output Data:**

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

![System Sequence Diagram](svg\us010-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* Client must Log In to make a Offer.