package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;


import beans.actorsBean;
import beans.directorsBean;
import helpers.jsonHelper;

public class actors {
	private Connection _connection;
	private ArrayList<actorsBean> _actors;

	private String createActors = "insert into actors (actor_fname,actor_lname,actor_age) values (?,?,?)";
	private String selectAllActors = "select * from actors";
	private String updateActorsAgeLname = "UPDATE actors SET actor_age = ?, actor_lname = ? WHERE actor_fname = ?";
	private String updateActorsLname = "UPDATE actors SET actor_lname = ? WHERE actor_fname = ?";
	private String deleteActors = "DELETE FROM actors WHERE actor_fname=?";

	public actors(Connection cn) {
		this._connection = cn;
		this._actors = new ArrayList<actorsBean>();
		getActors();
	}

	public ArrayList<actorsBean> getActors() {
		if (this._actors.size() > 0) 
			return this._actors;
			
		this._actors = new ArrayList<actorsBean>();
		try (PreparedStatement myQry = this._connection.prepareStatement(selectAllActors)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this._actors;
	}

	//Update
	public int updateActorAgeLname(String name, String newLname, int newAge) throws SQLException {
			String qry= "";
			qry = updateActorsAgeLname;
			int count = -1;
			try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
				String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
				myQry.setString(3, capName);

				if (newAge < 8) {
					System.out.println("Invalid age");
					return -1;
				} else {
					myQry.setInt(1, newAge);
					myQry.setString(2, newLname);
				}

				count = myQry.executeUpdate();
			} catch (SQLException e) {
				System.out.println("updateActors exception for statement");
				e.printStackTrace();
			}

			return count;
	}

	//Create
	public int createActor(String fname, String lname, int age) throws SQLException {
			String qry= "";
			qry = createActors;
			int count = -1;
			try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
				if (age < 8) {
					System.out.println("Actor to young.");
					return -1;
				} else {
					String capName = fname.substring(0, 1).toUpperCase() + fname.substring(1);
					myQry.setString(1, fname);
					myQry.setString(2, lname);
					myQry.setInt(3, age);
				}

				count = myQry.executeUpdate();
			} catch (SQLException e) {
				System.out.println("updateActors exception for statement");
				e.printStackTrace();
			}
			return count;

	}

	//Update
	public int updateActorLname(String fname, String newLname) throws SQLException {
			String qry= "";
			qry = updateActorsLname;
			int count = -1;
			try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
				String capFname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
				String capLname = newLname.substring(0, 1).toUpperCase() + newLname.substring(1);
				myQry.setString(1, capLname);
				myQry.setString(2, capFname);
				count = myQry.executeUpdate();
			} catch (SQLException e) {
				System.out.println("updateActors exception for statement");
				e.printStackTrace();
			}

			return count;

	}

	//Delete
	public int deleteActor(String fname) throws SQLException {
			String qry= "";
			qry = deleteActors;
			int count = -1;
			try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
				String capName = fname.substring(0, 1).toUpperCase() + fname.substring(1);
				myQry.setString(1, capName);
				count = myQry.executeUpdate();
			} catch (SQLException e) {
				System.out.println("updateActors exception for statement");
				e.printStackTrace();
			}
			return count;
	}

	//Read
	public void displayAllActors() throws SQLException {
		for(actorsBean ab : this._actors){
			System.out.println(ab.toString());
		}
	}

	public String toJson() {
		String beansContent = "";
		for (actorsBean ab : this._actors) {
			beansContent += ab.toJson() + ",";
		}
		return jsonHelper
			.toJsonArray("Actors", beansContent);
	}

 	private actorsBean buildActor(ResultSet rs) {
		actorsBean ab = new actorsBean();
		try {
			ab.setId(rs.getInt("actor_id"));
			ab.setFname(rs.getString("actor_fname"));
			ab.setLname(rs.getString("actor_lname"));
			ab.setAge(Integer.parseInt(rs.getString("actor_age")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}
	
 	private void buildActors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._actors.add(buildActor(rs));
		}
 	}

	 //Updates the ArrayList with fresh data from database
	 public void updateActorsList() throws SQLException {
		this._actors.clear();
		 PreparedStatement myQry = this._connection.prepareStatement(selectAllActors);
		 ResultSet rs = myQry.executeQuery();
		buildActors(rs);
	 }
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}
 	}

	public void printActorNames(){
		System.out.println("Actors : ");
		for(actorsBean ab : this._actors){
			System.out.println("Firstname : " + ab.getFname() + " " +
					"Lastname : " + ab.getLname());
		}
	}
}
