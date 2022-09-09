##Movie Booking Project

###Cardinality relationship between entities:
- One user can have multiple bookings. So user and booking has one to many mapping.
- Movie and Screen are provided as one to one mapping.
- Since Movie and Screen has one to one mapping. It can be shown in multiple cinemas. So Screen and Cinema has one to many mapping.
- User and Cinema has no mapping.Though User and Cinema provided as one to many mapping in the statement but was not clear about the fields and for implementing the functionality provided, I modified a bit.
- A Cinema can have many bookings. So one to many mapping between Cinema and Booking.

###Changes done for Resubmission
- Exception Handling
- Logging of exception information
- Implement Comparable interface
- Cascade Settings
- CRUD Operations
- JPA repository
- Transactional Management
- Msgs read from property files
