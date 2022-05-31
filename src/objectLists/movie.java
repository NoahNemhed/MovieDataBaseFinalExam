package objectLists;

import beans.actorsBean;
import beans.directorsBean;
import beans.genreBean;
import beans.moviesBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

public class movie {
    Connection _connection;
    private ArrayList<moviesBean> moviesList;

    private String createMovies = "call movies.add_movie(?, ?, ?, ?)";
    private String selectAllMovies = "select * from movies";
    private String updateMoviesName = "UPDATE movies SET movie_title = ? WHERE movie_title = ?";
    private String deleteMovies = "DELETE FROM movies WHERE movie_title=?";

    public movie(Connection _connection) throws SQLException {
        this._connection = _connection;
        this.moviesList = new ArrayList<moviesBean>();
        getMovies();
    }

    public ArrayList<moviesBean> getMovies() throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(selectAllMovies);
        ResultSet rs = query.executeQuery();
        while(rs.next()){
            moviesBean mb = new moviesBean();
            try {
                mb.setMovieId(rs.getInt("movie_id"));
                mb.setMovieTitle(rs.getString("movie_title"));
                mb.setReleaseDate(rs.getInt("release_date"));
                mb.setLength(rs.getInt("length_minutes"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.moviesList.add(mb);
        }
        return this.moviesList;
    }


    //Create
    public void createMovie(String Title, int releaseDate, int length, String dir_name) throws SQLException {
        CallableStatement query = this._connection.prepareCall(createMovies);
        System.out.println("Theese are the valid director names : ");
        query.setString(1,Title);
        query.setInt(2,releaseDate);
        query.setInt(3,length);
        query.setString(4, dir_name);
        query.executeUpdate();
        System.out.println("Movie created : " + " Title : " + Title + "\n" + "Director : " + dir_name );
    }

    //Delete
    public void deleteMovie(String Title) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(deleteMovies);
        query.setString(1, Title);
        query.executeUpdate();
        System.out.println("Movie : "+ Title + " has been deleted.");
    }

    //Update
    public void updateMovieName(String Title, String newTitle) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(updateMoviesName);
        query.setString(1,newTitle);
        query.setString(2,Title);
        query.executeUpdate();
        System.out.println("Movie : " + Title + " new title : " + newTitle);
    }

    //Read
    public void displayAllMovies(){
        for(moviesBean mb : this.moviesList){
            System.out.println(mb.toString());
        }
    }
    //Prints Movies titles
    public void printMovieTitles(){
        for(moviesBean mb : this.moviesList){
            System.out.println("Title : " + mb.getMovieTitle());
        }
    }

    //Updates the ArrayList with fresh data from database
    public void updateMovieList() throws SQLException {
        this.moviesList.clear();
        PreparedStatement myQry = this._connection.prepareStatement(selectAllMovies);
        ResultSet rs = myQry.executeQuery();
        while(rs.next()) {
            moviesBean mb = new moviesBean();
            try {
                mb.setMovieId(rs.getInt("movie_id"));
                mb.setMovieTitle(rs.getString("movie_title"));
                mb.setReleaseDate(rs.getInt("release_date"));
                mb.setLength(rs.getInt("length_minutes"));
                mb.setDirId(rs.getInt("dir_id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.moviesList.add(mb);
        }
    }
}
