INSERT INTO `moviebooking`.`screen` (`screen_id`, `type`) VALUES ('1', 'testType');

INSERT INTO `moviebooking`.`movie` (`movie_id`, `releaseDate`, `showCycle`, `title`, `screen_id`) VALUES ('1', '2022-08-19', 'testCycle', 'testMovie', '1');

INSERT INTO `moviebooking`.`cinema` (`cinema_id`, `booking_date`, `booking_time`, `isAvailable`, `name`, `seating_capacity`, `screen_id`) VALUES ('1', '2022-08-19', '12:00:00', 'Y', 'PVR', '100', '1');

INSERT INTO `moviebooking`.`user` (`user_id`, `password`, `user_name`) VALUES ('1', 'testpwd', 'testuser');

INSERT INTO `moviebooking`.`booking` (`booking_id`, `bookedSeats`, `is_active`, `cinema_id`, `user_id`) VALUES ('1', '12', 'Y', '1', '1');
