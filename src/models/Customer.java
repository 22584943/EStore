package models;


public class Customer {
	private int id;
	private String name;
	private Address address;
	private String email;
	private String telephone;
	private String hashedPassword;

	

	
	


	public Customer(String name, Address address, String email, String telephone, String hashedPassword ) {

		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.hashedPassword = hashedPassword;
	}
	public Customer(int id, String name, Address address, String email, String telephone) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
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
	public String getHashedPassword() {
		return this.hashedPassword;
	}

	public String toString() {
	return "{" + id + ", " + name + ", " + address + ", " + email + ", " + telephone + "}";
}
}