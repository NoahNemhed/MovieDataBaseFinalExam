-- Adding data
use movies;


-- Adding Directors

insert into directors (dir_fname, dir_lname, dir_age) values ('Frank', 'Darabont', 63);
insert into directors (dir_fname, dir_lname, dir_age) values ('Francis', 'Ford Coppola', 83);
insert into directors (dir_fname, dir_lname, dir_age) values ('Christopher', 'Nolan', 52);
insert into directors (dir_fname, dir_lname, dir_age) values ('Quentin', 'Tarantino', 59);
insert into directors (dir_fname, dir_lname, dir_age) values ('Robert', 'Zemeckis', 70);

-- Adding Actors

insert into actors (actor_fname, actor_lname, actor_age) values ('Tim', 'Robbins', 64);
insert into actors (actor_fname, actor_lname, actor_age) values ('Marlon', 'Brando', 98);
insert into actors (actor_fname, actor_lname, actor_age) values ('Christian', 'Bale', 48);
insert into actors (actor_fname, actor_lname, actor_age) values ('John', 'Travolta', 68);
insert into actors (actor_fname, actor_lname, actor_age) values ('Tom', 'Hanks', 65);

-- Adding Genres

insert into genre (genre_titel) values ('Drama');
insert into genre (genre_titel) values ('Horror');
insert into genre (genre_titel) values ('Action');
insert into genre (genre_titel) values ('Comedy');
insert into genre (genre_titel) values ('Thriller');


-- Adding Movies 

call add_movie('Nyckeln till frihet', 1994, 133,"Frank");
call add_movie('Gudfadern', 1972, 153,"Francis");
call add_movie('The Dark Knight', 2008, 139,"Christopher");
call add_movie('Pulp Fiction', 1994, 140,"Quentin");
call add_movie('Forrest Gump', 1994, 133,"Robert");


-- Adding Actors to Movies

call add_ActorToMovie('Tim', 'Nyckeln till frihet');
call add_ActorToMovie('Marlon', 'Gudfadern');
call add_ActorToMovie('Christian', 'The Dark Knight');
call add_ActorToMovie('John', 'Pulp Fiction');
call add_ActorToMovie('Tom', 'Forrest Gump');


-- Adding Genre to Movies
call add_GenreToMovie("Drama", "Nyckeln till frihet");
call add_GenreToMovie("Action", "Gudfadern");
call add_GenreToMovie("Action", "The Dark Knight");
call add_GenreToMovie("Action", "Pulp Fiction");
call add_GenreToMovie("Comedy", "Forrest Gump");
