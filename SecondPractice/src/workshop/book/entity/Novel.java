package workshop.book.entity;

public class Novel extends Publication{

	private String author;
	private String genre;
	public Novel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Novel(String title, String publishingData, int page, int price,String author, String genre) {
		super(title,publishingData,page,price);
		this.author = author;
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
}
