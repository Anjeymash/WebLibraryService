package by.htp.library.bean;

import java.io.Serializable;

/**
 * Entity class serves to store book-objects with properties
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String genre;
	private String year;
	private int quantity;
	private long id;
	private String status;
	private String context;

	public Book() {
	}

	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Entity class-constructor for book-objects initializing
	  */
	
	public Book(long id, String title, String author, String genre, String year, int quantity, String status,
			String context) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
		this.id = id;
		this.quantity = quantity;
		this.status = status;
		this.context = context;

	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (quantity != other.quantity)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	/**
	 *  for book-objects initializing
	  */
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + " genre=" + genre + ", year=" + year
				+ ", quantity=" + quantity + ", status=" + status + "]";
	}
}