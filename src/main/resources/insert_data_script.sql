INSERT INTO `moviebooking`.`screen` (`screen_id`, `type`) VALUES ('1', 'testType');

INSERT INTO `moviebooking`.`movie` (`movie_id`, `release_date`, `show_cycle`, `title`, `screen_id`) VALUES ('1', '2022-08-19', 'testCycle', 'testMovie', '1');

INSERT INTO `moviebooking`.`cinema` (`cinema_id`, `booking_time`, `is_available`, `name`, `seating_capacity`, `screen_id`) VALUES ('1', '12:00:00', 'Y', 'PVR', '100', '1');

INSERT INTO `moviebooking`.`user` (`user_id`, `password`, `user_name`) VALUES ('1', 'testpwd', 'testuser');

INSERT INTO `moviebooking`.`booking` (`booking_id`, `booked_seats`, `is_active`, `cinema_id`, `user_id`) VALUES ('1', '12', 'Y', '1', '1');
