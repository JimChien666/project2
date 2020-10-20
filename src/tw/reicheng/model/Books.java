package tw.reicheng.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books {
	private int id;
	private String bookname;
	private String author;
	private int price;
	private BooksDetail booksDetail;
	
	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "BOOKNAME")
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	@Column(name = "AUTHOR")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name = "PRICE")
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "books",cascade = CascadeType.ALL)
	public BooksDetail getBooksDetail() {
		return booksDetail;
	}
	public void setBooksDetail(BooksDetail booksDetail) {
		this.booksDetail = booksDetail;
	}
	
}
