package objectLists;

import beans.directorsBean;
import beans.genreBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class genre {
    Connection _connection;
    private ArrayList<genreBean> genreList;

    private String createGenres = "insert into genre (genre_titel) values (?)";
    private String selectAllGenres = "select * from genre";
    private String updateGenresTitel = "UPDATE genre SET genre_titel = ? WHERE genre_titel = ?";
    private String deleteGenre = "DELETE FROM genre WHERE genre_titel=?";


    public genre(Connection _connection) throws SQLException {
        this._connection = _connection;
        this.genreList = new ArrayList<genreBean>();
        getGenres();
    }

    private ArrayList<genreBean> getGenres() throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(selectAllGenres);
        ResultSet rs = query.executeQuery();
        while(rs.next()){
            genreBean gb = new genreBean();
            try {
                gb.setTitel(rs.getString("genre_titel"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.genreList.add(gb);
        }
        return this.genreList;
    }

    //Create
    public void createGenre(String Titel) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(createGenres);
        System.out.println(Titel);
        query.setString(1,Titel);
        query.executeUpdate();
        System.out.println("Genre : " + Titel + " has been created");
    }

    //Delete
    public void deleteGenre(String Titel) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(deleteGenre);
        query.setString(1,Titel);
        query.executeUpdate();
        System.out.println("Genre : " + Titel + " has been Deleted");
    }

    //Update
    public void updateGenreTitel(String Titel, String newTitel) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(updateGenresTitel);
        query.setString(1,newTitel);
        query.setString(1,Titel);
        query.executeUpdate();
        System.out.println("Genre : " + Titel + " has been updated to " + newTitel);
    }

    //Read
    public void displayAllGenres(){
        for(genreBean gb : genreList){
            System.out.println(gb.toString());
        }
    }

    //Updates the ArrayList with fresh data from database
    public void updateGenreList() throws SQLException {
        this.genreList.clear();
        PreparedStatement myQry = this._connection.prepareStatement(selectAllGenres);
        ResultSet rs = myQry.executeQuery();
        while(rs.next()) {
            genreBean gb = new genreBean();
            try {
                gb.setId(rs.getInt("genre_id"));
                gb.setTitel(rs.getString("genre_titel"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.genreList.add(gb);
        }
    }
}
