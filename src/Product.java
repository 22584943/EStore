import java.util.UUID;

public class Product {
	private int id;
 private String SKU; //stock keeping unit (a unique code for each product)
 private int stock;
 private String name;
 private String description;
 private String category;
 private int price;


	public Product(int id, String SKU, String category, String name, String description, int price, int stock) {
		this.id = id;
		this.SKU = SKU;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}



	// Getters


	public int getID() {
		return id;
	}

	public String get() {
		return this.toString();
	}
	public String getSKU() {
		return SKU;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getPrice() {
		return price;
	}
	
	
	public int getStock( ) {
		return stock;
	}
	// Setters
	
	public void setSKU(String newSKU) {
		this.SKU = newSKU;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public void setCategory(String newCategory) {
		this.category = newCategory;
	}
	
	public void setPrice(int newPrice) {
		this.price = newPrice;
	}
	
	public void setStock(int newStock ) {
		this.stock = newStock;
	}
	// toString

	public String toString() {
		return "SKU: " + SKU + ", CATEGORY: " + category + " , NAME: " + name + ", DESCRIPTION: " + description + ", PRICE: " + price;
	}
	
	
}