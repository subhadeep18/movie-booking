##Movie Booking Project

###Cardinality relationship between entities:
- One user can have multiple bookings. So user and booking has one to many mapping.
- Movie and Screen are provided as one to one mapping.
- Since Movie and Screen has one to one mapping. It can be shown in multiple cinemas. So Screen and Cinema has one to many mapping.
- User and Cinema has no mapping.Though User and Cinema provided as one to many mapping in the statement but was not clear about the fields and for implementing the functionality provided, I modified a bit.
- A Cinema can have many bookings. So one to many mapping between Cinema and Booking.

###Changes done for Resubmission
- Exception Handling (added RestExceptionHandler.java in a new package, removed try catch blocks in code)
- Logging of exception information ( added log error messages as well as log info messages for production support using logback, added logback configurations)
- Implement Comparable interface ( for all model classes )
- Cascade Settings ( modified cascade settings )
- CRUD Operations ( Added date and time for cinema for booking and seatcheckavailability function )
- JPA repository ( Used Pageable for Booking, Cinema and Movie, @Query annotation in Cinema for seatAvailability check )
- Transactional Management ( added DataSourceTransactionConfig.java and used @Transactional annotation for all service implementations instead of interface )
- Msgs read from property files  ( added exception messages in the message.properties file and read from it )
