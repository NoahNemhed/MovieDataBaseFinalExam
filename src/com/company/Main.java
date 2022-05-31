package com.company;


import helpers.databaseHelper;
import objectLists.actors;
import objectLists.directors;
import objectLists.genre;
import objectLists.movie;


import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void runApp() throws SQLException {
        //Decalring objects
        Connection conn = databaseHelper.DbConnect("movies");
        Scanner sc = new Scanner(System.in);
        actors a = new actors(conn);
        movie m = new movie(conn);
        directors d = new directors(conn);
        genre g = new genre(conn);
        //Start condition.
        boolean start = true;
        System.out.println("Hello welcome to my database app.");
        while(start){
            System.out.println("Press 0 to QUIT app! " + "\n" +
                               "Press 1 to create an actor " + "\n" +
                               "Press 2 to update an existing actors 'Lastname' " + "\n" +
                               "Press 3 to update an existing actors 'Lastname' and 'Age'" + "\n" +
                               "Press 4 to delete an existing actor." + "\n" +
                               "Press 5 to read all actors." + "\n" +
                               "--------------------------------------------" + "\n" +
                               "Press 6 to create an director" + "\n" +
                               "Press 7 to update an existing directors 'Age' " + "\n" +
                               "Press 8 to update an existing directors 'Lastname' " + "\n" +
                               "Press 9 to delete an existing director. " + "\n" +
                               "Press 10 to read all directors." + "\n" +
                               "---------------------------------------------" + "\n" +
                               "Press 11 to create an genre. " + "\n" +
                               "Press 12 to update an existing genres 'Title'. " + "\n" +
                               "Press 13 to delete an existing genre. " + "\n" +
                               "Press 14 to read all genres. " + "\n" +
                               "---------------------------------------------" + "\n" +
                               "Press 15 to create an Movie. " + "\n" +
                               "Press 16 to update an existing movies 'Title'. " + "\n" +
                               "Press 17 to delete an existing movie. "+ "\n" +
                               "Press 18 to read all movies." + "\n" +
                               "---------------------------------------------");
            int ans = sc.nextInt();
            switch(ans){
                case 0 : {
                    System.out.println("Quitting.....");
                    start = false;
                }
                case 1 : {
                    System.out.println("Enter Actors FirstName.");
                    String fname = sc.next();
                    System.out.println("Enter Actors LastName.");
                    String lname = sc.next();
                    System.out.println("Enter Actors age");
                    int age = Integer.parseInt(sc.next());
                    a.createActor(fname, lname, age);
                    System.out.println("Actor created");
                    a.updateActorsList();
                    break;
                }

                case 2 : {
                    System.out.println("Enter the FirstName of actor." + "\n" +
                                "Here are the existing actors first and lastname.");
                    a.printActorNames();
                    String fname = sc.next();
                    System.out.println("Enter new Lastname");
                    String newLname = sc.next();
                    a.updateActorLname(fname,newLname);
                    System.out.println("Actors LastName has been updated.");
                    a.updateActorsList();
                    break;

                }

                case 3 : {
                    System.out.println("Enter the FirstName of actor." + "\n" +
                            "Here are the existing actors first and lastname.");
                    a.printActorNames();
                    String fname = sc.next();
                    System.out.println("Enter new LastName");
                    String newLname = sc.next();
                    System.out.println("Enter new Age");
                    int newAge = Integer.parseInt(sc.next());
                    a.updateActorAgeLname(fname,newLname,newAge);
                    System.out.println("Actors LastName and Age has been updated.");
                    a.updateActorsList();
                    break;
                }

                case 4 : {
                    System.out.println("Enter the FirstName of actor." + "\n" +
                            "Here are the existing actors first and lastname.");
                    a.printActorNames();
                    String fname = sc.next();
                    a.deleteActor(fname);
                    System.out.println("Actor " + fname + " has been removed.");
                    a.updateActorsList();
                    break;
                }

                case 5 : {
                    a.displayAllActors();
                    break;
                }

                case 6 : {
                    System.out.println("Enter Directors FirstName.");
                    String fname = sc.next();
                    System.out.println("Enter Directors LastName.");
                    String lname = sc.next();
                    System.out.println("Enter Directors age");
                    int age = Integer.parseInt(sc.next());
                    d.createDirector(fname,lname,age);
                    System.out.println("Director created");
                    d.updateDirectorsList();
                    break;
                }

                case 7 : {
                    System.out.println("Enter FirstName of director");
                    d.printDirectsNames();
                    String fname = sc.next();
                    System.out.println("Enter new age for director");
                    int age = Integer.parseInt(sc.next());
                    d.updateDirectorAge(fname,age);
                    System.out.println("Director "+ fname + " age has been changed");
                    d.updateDirectorsList();
                    break;

                }

                case 8 : {
                    System.out.println("Enter FirstName of director");
                    d.printDirectsNames();
                    String fname = sc.next();
                    System.out.println("Enter new LastName");
                    String newLname = sc.next();
                    d.updateDirectorLname(fname, newLname);
                    System.out.println("Director " + fname + " LastName has been changed.");
                    d.updateDirectorsList();
                    break;
                }

                case 9 : {
                    System.out.println("Enter FirstName of director");
                    d.printDirectsNames();
                    String fname = sc.next();
                    d.deleteDirector(fname);
                    System.out.println("Director " + fname + " has been removed.");
                    d.updateDirectorsList();
                    break;
                }

                case 10 : {
                    d.displayAllDirectors();
                    break;
                }

                case 11 : {
                    System.out.println("Enter title of genre");
                    String title = sc.next();
                    g.createGenre(title);
                    System.out.println("New genre : " + title + " has been created.");
                    g.updateGenreList();
                    break;
                }

                case 12 : {
                    System.out.println("Enter old title of genre heres a list of existing genres.");
                    g.displayAllGenres();
                    String title = sc.next();
                    System.out.println("Enter new title");
                    String newTitle = sc.next();
                    g.updateGenreTitel(newTitle,title);
                    System.out.println(title + " has been changed to " + newTitle);
                    g.updateGenreList();
                    break;
                }

                case 13 : {
                    System.out.println("Enter title of genre heres a list of existing genres");
                    g.displayAllGenres();
                    String title = sc.next();
                    g.deleteGenre(title);
                    System.out.println(title + " has been removed.");
                    g.updateGenreList();
                    break;
                }

                case 14 : {
                    g.displayAllGenres();
                    break;
                }

                case 15 : {
                    System.out.println("Enter movie title.");
                    String title = sc.next();
                    System.out.println("Enter release date format {YYYY}");
                    int releaseDate = Integer.parseInt(sc.next());
                    System.out.println("Enter length of movie");
                    int length = Integer.parseInt(sc.next());
                    System.out.println("Enter director of movie heres a list of available directors.");
                    d.printDirectsNames();
                    String dirFname = sc.next();
                    m.createMovie(title,releaseDate,length, dirFname);
                    System.out.println("Movie " + title + " has been created.");
                    m.updateMovieList();
                    break;
                }

                case 16 : {
                    System.out.println("Enter current movie title heres a list of existing movies.");
                    m.displayAllMovies();
                    String oldTitle = sc.next();
                    System.out.println("Enter new title for movie. ");
                    String newTitle = sc.next();
                    m.updateMovieName(newTitle,oldTitle);
                    System.out.println("Movie title has been change from " + oldTitle + " to "+ newTitle);
                    m.updateMovieList();
                    break;
                }

                case 17 : {
                    System.out.println("Enter movie title heres a list of existing movies.");
                    m.displayAllMovies();
                    String Title = sc.next();
                    m.deleteMovie(Title);
                    System.out.println(Title + " Has been removed.");
                    m.updateMovieList();
                    break;
                }

                case 18 : {
                    m.displayAllMovies();
                    break;
                }

            }
        }




    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    runApp();




    }
}
