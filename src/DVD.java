

public class DVD {
	
	private int id;
	private String title;
	private String genre;
	private int year;
	
	
	public DVD(int ID, String title, String genre, int year) {
		
		this.id = ID;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getGenre() {
		return this.genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "DVD [ID=" + this.id + ", Title=" + this.title + ", Genre=" + this.genre + ", Year=" + this.year + "]";
	}
	
}
