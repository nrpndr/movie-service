-- create table users if it does not exist
CREATE TABLE IF NOT EXISTS `db_movies`.`movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `release_date` varchar(10) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `movie_duration_in_minutes` int NOT NULL,
  `director` varchar(100) NOT NULL,
  `description` varchar(5000) NOT NULL,
  `cast` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;