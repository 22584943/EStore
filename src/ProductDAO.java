import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProductDAO {

	public ProductDAO() {}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:everythingStoreDB.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	public ArrayList<Product> getProducts() throws SQLException {
		System.out.println("Retrieving all products ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM products;";
		ArrayList<Product> products = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {
				int id = result.getInt("id");
				String SKU = result.getString("SKU");
				String category = result.getString("category");
				String name = result.getString("name");
				String description = result.getString("description");
				int price = result.getInt("price");
				int stock = result.getInt("stock");
				products.add(new Product(id, SKU, category, name, description, price, stock));
			}
		} catch(Exception e) {
			System.out.println("get all dvds: "+e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return products;
	}

//	public DVD getDVD(int film_id) throws SQLException {
//
//		DVD temp = null;
//		Connection dbConnection = null;
//		Statement statement = null;
//		ResultSet result = null;
//
//		String query = "SELECT * FROM collection WHERE ID =" + film_id + ";";
//
//		try {
//			dbConnection = getDBConnection();
//			statement = dbConnection.createStatement();
//		//	System.out.println("DBQuery: " + query);
//			// execute SQL query
//			result = statement.executeQuery(query);
//
//			while (result.next()) {
//
//
//				int id = result.getInt("ID");
//				String title = result.getString("Title");
//				String genre = result.getString("Genre");
//				int year = result.getInt("Year");
//        int rating = result.getInt("Rating");
//
//				temp = new DVD(id,title,genre,year,rating);
//
//			}
//		} finally {
//			if (result != null) {
//				result.close();
//			}
//			if (statement != null) {
//				statement.close();
//			}
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
//		}
//		return temp;
//	}
//
//	public Boolean deleteDVD(int film_id) throws SQLException {
//		System.out.println("Deleting dvd");
//		Connection dbConnection = null;
//		Statement statement = null;
//		int result = 0;
//		String query = "DELETE FROM collection WHERE ID = " + film_id + ";";
//		try {
//			dbConnection = getDBConnection();
//			statement = dbConnection.createStatement();
//		//	System.out.println(query);
//			// execute SQL query
//			result = statement.executeUpdate(query);
//		} finally {
//			if (statement != null) {
//				statement.close();
//			}
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
//		}
//		if (result == 1) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public Boolean updateDVD(DVD dvd) throws SQLException {
//		Connection dbConnection = null;
//		Statement statement = null;
//
//		String query = "UPDATE collection " + "SET ID = '" + dvd.getID() + "'," + "Title = '"
//				+ dvd.getTitle() + "'," + "Genre= '" + dvd.getGenre() + "'," + "Year= " + dvd.getYear() + ", Rating=" + dvd.getRating() + " WHERE ID = " + dvd.getID()
//				+ ";";
//
//		try {
//			dbConnection = getDBConnection();
//			statement = dbConnection.createStatement();
//			//System.out.println(query);
//			// execute SQL update
//			statement.executeUpdate(query);
//
//		} catch (SQLException e) {
//
//			System.out.println(e.getMessage());
//			return false;
//
//		} finally {
//
//			if (statement != null) {
//				statement.close();
//			}
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
//		}
//		return true;
//	}
//
//	public boolean addDVD(DVD in) throws SQLException{
//		Connection dbConnection = null;
//		Statement statement = null;
//
//		String update = "INSERT INTO collection (ID, Title, Genre, Year, rating) VALUES ("+in.getID()+",'"+ in.getTitle()+"','"+in.getGenre()+"',"+in.getYear()+","+in.getRating() + ");";
//		boolean ok = false;
//			try {
//					dbConnection = getDBConnection();
//					statement = dbConnection.createStatement();
//					System.out.println(update);
//		// execute SQL query
//					statement.executeUpdate(update);
//					ok = true;
//				} catch (SQLException e) {
//					System.out.println(e.getMessage());
//				} finally {
//					if (statement != null) {
//						statement.close();
//					}
//					if (dbConnection != null) {
//						dbConnection.close();
//					}
//
//				}
//			return ok;
//	}
}