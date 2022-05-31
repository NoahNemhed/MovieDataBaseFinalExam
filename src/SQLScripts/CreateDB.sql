drop database if exists movies;

create database if not exists movies;

use movies;


drop table if exists directors;
create table if not exists directors (
dir_id INT AUTO_INCREMENT,
dir_fname varchar(50) NOT NULL,
dir_lname varchar(50) NOT NULL,
dir_age INT NOT NULL,
PRIMARY KEY (dir_id)
);

drop table if exists actors;
create table if not exists actors (
actor_id INT AUTO_INCREMENT,
actor_fname VARCHAR(50) NOT NULL,
actor_lname VARCHAR(50) NOT NULL,
actor_age INT NOT NULL,
PRIMARY KEY (actor_id)
);

drop table if exists genre;
create table if not exists genre (
genre_id INT AUTO_INCREMENT,
genre_titel varchar(50) NOT NULL,
PRIMARY KEY (genre_id)
);

drop table if exists movies;
create table if not exists movies (
movie_id INT AUTO_INCREMENT,
movie_title VARCHAR(50) NOT NULL,
release_date INT NOT NULL,
length_minutes INT NOT NULL,
dir_id INT NOT NULL,
foreign key (dir_id) references directors (dir_id),
PRIMARY KEY (movie_id)
);

drop table if exists movie_actor;
create table if not exists movie_actor(
    movie_id int not null,
    actor_id int not null,
    foreign key (movie_id) references movies (movie_id),
    foreign key (actor_id) references actors (actor_id)
);

drop table if exists movie_genre;
create table if not exists movie_genre(
    movie_id int not null,
    genre_id int not null,
    foreign key (movie_id) references movies (movie_id),
    foreign key (genre_id) references genre (genre_id)
);
