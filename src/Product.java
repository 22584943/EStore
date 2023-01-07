import java.util.UUID;

public class Product {
	private int id;
 private String SKU; //stock keeping unit (a unique code for each product)
 private int stock;
 private String name;
 private String description;
 private String category;
 private double price;

 private int quantityOrdered;


	public Product(int id, String SKU, String category, String name, String description, double price, int stock) {
		this.id = id;
		this.SKU = SKU;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	//override for creating product instance before it exists in db where it gets given id
	public Product(String SKU, String category, String name, String description, double price, int stock) {

		this.SKU = SKU;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	// override for basket product to show
	public Product(int id, int quantity, String SKU, String category, String name, String description, double price, int currentStock) {
		this.id = id;
		this.SKU = SKU;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.quantityOrdered = quantity;
		this.stock = currentStock;
	}

	// override for basket product
	public Product(int id, int quantity) {
		this.id = id;
		this.quantityOrdered = quantity;
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
	
	public double getPrice() {
		return price;
	}

	public int getQuantityOrdered( ) {
		return quantityOrdered;
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

	public void setQuantityOrdered(int q ) {
		this.quantityOrdered = q;
	}
	public void setStock(int newStock ) {
		this.stock = newStock;
	}
	// toString

	public String toString() {
		return "id: " + id + ", SKU: " + SKU + ", CATEGORY: " + category + " , NAME: " + name + ", DESCRIPTION: " + description + ", PRICE: " + price + ", STOCK:" + stock;
	}
	
	
}