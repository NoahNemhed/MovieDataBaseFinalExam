package objectLists;

import beans.actorsBean;
import beans.directorsBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class directors {
    private Connection _connection;
    private ArrayList<directorsBean> directors;

    private String createDirectors = "insert into directors (dir_fname,dir_lname,dir_age) values (?,?,?)";
    private String selectAllDirectors = "select * from directors";
    private String updateDirectorsAge = "UPDATE directors SET dir_age = ?  WHERE dir_fname = ?";
    private String updateDirectorsLname = "UPDATE directors SET dir_lname = ? WHERE dir_fname = ?";
    private String deleteDirectors = "DELETE FROM directors WHERE dir_fname=?";


    public directors(Connection _connection) throws SQLException {
        this._connection = _connection;
        this.directors = new ArrayList<directorsBean>();
        getDirectors();
    }

    public directors() throws SQLException {
        this.directors = new ArrayList<directorsBean>();
        getDirectors();
    }


    public ArrayList<directorsBean> getDirectors() throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(selectAllDirectors);
        ResultSet rs = query.executeQuery();
        while(rs.next()){
            directorsBean db = new directorsBean();
            try {
                db.setId(rs.getInt("dir_id"));
                db.setFname(rs.getString("dir_fname"));
                db.setLname(rs.getString("dir_lname"));
                db.setAge(Integer.parseInt(rs.getString("dir_age")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.directors.add(db);
        }
        return directors;
    }

    //Create
    public void createDirector(String fname,String lname,int age) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(createDirectors);
        String capFname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
        String capLname = lname.substring(0, 1).toUpperCase() + lname.substring(1);
        query.setString(1, capFname);
        query.setString(2, capLname);
        query.setInt(3, age);
        query.executeUpdate();
        System.out.println("Director : "+ fname + " " + lname + " age " + age + " created.");
    }

    //Delete
    public void deleteDirector(String fname) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(deleteDirectors);
        String capFname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
        query.setString(1, capFname);
        query.executeUpdate();
        System.out.println("Director : "+ capFname + " has been removed.");
    }

    //Update
    public void updateDirectorAge(String fname, int newAge) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(updateDirectorsAge);
        String capFname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
        query.setInt(1, newAge);
        query.setString(2, capFname);
        query.executeUpdate();
        System.out.println("Director : " + capFname + " new age : " + newAge);
    }

    //Update
    public void updateDirectorLname(String fname, String newLname) throws SQLException {
        PreparedStatement query = this._connection.prepareStatement(updateDirectorsLname);
        String capFname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
        String capLname = newLname.substring(0, 1).toUpperCase() + newLname.substring(1);
        query.setString(1, capLname);
        query.setString(2, capFname);
        query.executeUpdate();
        System.out.println("Director : " + capFname + " new lastname : " + capLname);
    }

    //Read
    public void displayAllDirectors(){
        for(directorsBean db : directors){
            System.out.println(db.toString());
        }
    }

    //Prints director names
    public void printDirectsNames(){
        System.out.println("Directors : ");
        for(directorsBean db : directors){
            System.out.println("Firstname : " + db.getFname() + " " +
                                "Lastname : " + db.getLname());
        }
    }

    //Updates the ArrayList with fresh data from database
    public void updateDirectorsList() throws SQLException {
        this.directors.clear();
        PreparedStatement myQry = this._connection.prepareStatement(selectAllDirectors);
        ResultSet rs = myQry.executeQuery();
        while(rs.next()) {
            directorsBean db = new directorsBean();
            try {
                db.setId(rs.getInt("dir_id"));
                db.setFname(rs.getString("dir_fname"));
                db.setLname(rs.getString("dir_lname"));
                db.setAge(Integer.parseInt(rs.getString("dir_age")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.directors.add(db);
        }
    }


}
