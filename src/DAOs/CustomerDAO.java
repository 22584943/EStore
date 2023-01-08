package DAOs;

import models.Address;
import models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerDAO {
	
	public CustomerDAO() {}
	
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
	
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		System.out.println("Retrieving all customers ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM customers;";
		ArrayList<Customer> customers = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String address =result.getString("address");
				String[] addressSplit = address.split(",");
				Address objAddress = new Address(addressSplit[0],addressSplit[1],addressSplit[2],addressSplit[3],addressSplit[4],addressSplit[5]);
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				
				customers.add(new Customer(id,name, objAddress, email, telephone));
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
		return customers;
	}

	public Customer getCustomerByID(int customerID) throws SQLException {

		Customer temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM customers WHERE ID =" + customerID + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//	System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {


				int id = result.getInt("id");
				String name = result.getString("name");
				String address = result.getString("address");
				String email = result.getString("email");
				String[] addressSplit = address.split(",");
				Address objAddress = new Address(addressSplit[0],addressSplit[1],addressSplit[2],addressSplit[3],addressSplit[4],addressSplit[5]);
				String telephone = result.getString("telephone");

				temp = new Customer(id,name, objAddress, email, telephone);

			}
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
		return temp;
	}

	public Boolean updateCustomer(Customer customer) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE customers " + "SET name='" + customer.getName() + "'," + "address='"
				+ customer.getAddress() + "'," + "email='" + customer.getEmail() + "'," +  "telephone='" + customer.getTelephone() +  "' WHERE ID=" + customer.getID()
				+ ";";
		System.out.println(query);
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println(query);
			// execute SQL update
			statement.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}
	public Boolean deleteCustomer(int customerID) throws SQLException {
		System.out.println("Deleting dvd");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM customers WHERE ID = " + customerID + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
		//	System.out.println(query);
			// execute SQL query
			result = statement.executeUpdate(query);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addCustomer(Customer c) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;

		String update = "INSERT INTO customers (name, address, email, telephone) VALUES ('"+ c.getName()+"','"+c.getAddress()+"','"+c.getEmail()+"','"+c.getTelephone() + "');";
		boolean ok = false;
			try {
					dbConnection = getDBConnection();
					statement = dbConnection.createStatement();
					System.out.println(update);
		// execute SQL query
					statement.executeUpdate(update);
					ok = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					if (statement != null) {
						statement.close();
					}
					if (dbConnection != null) {
						dbConnection.close();
					}

				}
			return ok;
	}
	}