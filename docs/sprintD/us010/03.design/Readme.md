# US 010 - Client place an order to purchase the property

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...      | Answer                   | Justification (with patterns)                                                                                 |
|:---------------|:-------------------------------------------------|:-------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  	      | ... interacting with the actor?                  | MakeOfferUI              | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                         | MakeOfferController      | Controller                                                                                                    |
|                | ... knowing the user using the system?           | UserSession              | IE: cf. A&A component documentation.                                                                          |
| Step 2         | ... get property Type                            | PropertyTypeEnum         | Repository of the Type of Property in the Company (Information Expert)                                        |
|                | ... display property Type to choose              | MakeOfferUI              | interacts with the actor                                                                                      |
| 	              | ... chooses property Type                        | User (actor)             | knows the information                                                                                         |
| Step 3         | ... get Business Type                            | BusinessTypeEnum         | Repository of the Type of Business for the Property (Sale or Rent) (Information Expert)                       |
|                | ... display Business Type to choose              | MakeOfferUI              | interacts with the actor                                                                                      |
| 	              | ... chooses Business Type                        | User (actor)             | knows the information                                                                                         |
| Step 4         | ... get Advertisement List                       | AdvertisementRepository  | Repository of the Advertisements in the Company (Information Expert)                                          |
|                | ... transforms Advertisement in AdvertisementDTO | AdvertisementMapper      | Creator                                                                                                       |
|                | ... create Advertisement DTO List                | AdvertisementCollections | creates List of AdvertisementDTO    (Information Expert)                                                      |
|                | ... display Advertisement DTO List to choose     | MakeOfferUI              | interacts with the actor                                                                                      |
| 	              | ... chooses Advertisement DTO                    | User (actor)             | knows the information                                                                                         |
| Step 5         | ... ask the value of the Offer                   | MakeOfferUI              | interacts with the actor                                                                                      |
|                | ... indicates the amount of the offer            | User (actor)             | knows the information                                                                                         |
|                | ... transforms AdvertisementDTO in Advertisement | AdvertisementMapper      | seeks Ad in the AdvertisementDTO (Information Expert)                                                         |
|                | ... validates value for the Offer                | AdvertisementRepository  | Repository of the Advertisements in the Company (Information Expert)                                          |
| Step 6         | ... display Offer conditions ask for validation  | MakeOfferUI              | interacts with the actor                                                                                      |
|                | ... validates the conditions                     | User (actor)             | knows the information                                                                                         |
| Step 8         | ... Create New Offer in the Advertisement        | AdvertisementRepository  | Repository of the Advertisements in the Company (Information Expert)                                          |
| Step 9         | ... display operation success                    | MakeOfferUI              | Is responsible for user interactions.                                                                         | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* PropertyTypeRepository
* BusinessTypeRepository
* AdvertisementRepository


Other software classes (i.e. Pure Fabrication) identified:

* MakeOfferUI
* MakeOfferController
* AdvertisementMapper
* AdvertisementCollections


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us010-sequence-diagram-ful.svg)

### Alternative 2 - Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.

![Sequence Diagram - split](svg/us010-sequence-diagram-split.svg)

**Get Repositories Acess Partial SD**

![Sequence Diagram - Partial - Get Repositories Acess](svg/us0101-sequence-diagram-partial-get-repositories.svg)

**Get Property Type List**

![Sequence Diagram - Partial - Get Property Type List](svg/us0102-sequence-diagram-partial-get-property-type-list.svg)

**Get Business Type List**

![Sequence Diagram - Partial - Get Business Type List](svg/us0103-sequence-diagram-partial-get-business-type-list.svg)

**Get AdvertismentDTOList Sorted By Date**

![Sequence Diagram - Partial - Get AdvertismentDTOList Sorted By Date](svg/us0104-sequence-diagram-partial-get-advertismentDTOList-sorted-by-date.svg)

**Get Client Email**

![Sequence Diagram - Partial - Get Client Email](svg/us0105-sequence-diagram-partial-get-client-email.svg)

**Verify Offer Validation**

![Sequence Diagram - Partial - Verify Offer Validation](svg/us0106-sequence-diagram-partial-verify-offer-validation.svg)

**Add New Offer to Advertisement**

![Sequence Diagram - Partial - Add New Offer to Advertisement](svg/us0107-sequence-diagram-partial-ad-new-offer.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us010-class-diagram.svg)