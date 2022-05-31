use movies;

DROP PROCEDURE IF EXISTS add_GenreToMovie;
DELIMITER $$
create procedure add_GenreToMovie( IN genreTitel varchar(50), IN movieTitel varchar(50) )
BEGIN
insert into movie_genre (movie_id, genre_id)
select movies.movie_id, genre.genre_id
from movies 
join genre where genre.genre_titel=genreTitel and movies.movie_title=movieTitel;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS add_movie;
DELIMITER $$
CREATE PROCEDURE add_movie (IN title varchar(50), IN movie_release_date INT, movie_length INT, IN dir_name varchar(50))
BEGIN
insert into movies (movie_title, release_date, length_minutes, dir_id)
select title, movie_release_date, movie_length, 
dir_id from directors where dir_fname=dir_name;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS add_ActorToMovie;
DELIMITER $$
create procedure add_ActorToMovie( IN fname varchar(50), IN title varchar(50) )
BEGIN
insert into movie_actor (movie_id, actor_id)
select movies.movie_id, actors.actor_id 
from movies 
join actors where actors.actor_fname=fname and movies.movie_title=title;
END $$
DELIMITER ;
