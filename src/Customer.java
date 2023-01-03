import java.util.ArrayList;
import java.util.Random;



public class Customer {
	private int id;
	private String name;
	private Address address;
	private String email;
	private String telephone;
//	private static ArrayList<Customer> customers;
	static Random r = new Random();
	static int n = r.nextInt(5000);
	
	public static int getNewCustomerID(ArrayList<Customer>customers) {
		
		
		// check for unique IDs
		for (Customer c : customers) {
			if (n == c.getID()) {
				n = r.nextInt(5000);
				getNewCustomerID(customers);
			} 
		}
		return n;
		
	}
	
	
	public Customer(String name, Address address, String email, String telephone, ArrayList<Customer> customers) {

			this.id = getNewCustomerID(customers);
			this.name = name;
			this.address = address;
			this.email = email;
			this.telephone = telephone;
	//		Customer.customers = customers;
		}

	public Customer(String name, Address address, String email, String telephone ) {

		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		//		Customer.customers = customers;
	}
	public Customer(int id, String name, Address address, String email, String telephone) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
	//	Customer.customers = customers;
	}
	public Customer getCustomer() {
		return this;
	}

	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public Address getAddress() {
		return this.address;
	}
	public String getEmail() {
		return this.email;
	}
	public String getTelephone() {
		return this.telephone;
	}

	public String toString() {
	return "{" + id + ", " + name + ", " + address + ", " + email + ", " + telephone + "}";
}
}