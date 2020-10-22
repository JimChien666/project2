package team6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "booksDetail")
public class BooksDetail {
	private int id;
	private String publisher;
	private String publisheraddress;	
	private Books books;
	
	@GenericGenerator(name = "generator",strategy = "foreign",
			parameters = @Parameter(name = "property",value = "books"))
	@Id @GeneratedValue(generator = "generator")
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "PUBLISHER")
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Column(name = "PUBLISHERADDRESS")
	public String getPublisheraddress() {
		return publisheraddress;
	}
	public void setPublisheraddress(String publisheraddress) {
		this.publisheraddress = publisheraddress;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	
	
}
