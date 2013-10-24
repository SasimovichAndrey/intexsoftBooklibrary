package intexsoftBookLibrary.library;

import java.util.Date;

public class Book {
	
	private Integer ID;
	private String author;
	private String title;
	private Date issueDate;
	private String reader;
	
	//private LibraryDescriptor libDescriptor;
	private Library library;
	
	public Book(){
		super();
	}
	
	public Book(Integer iD, String author, String title) {
		super();
		this.ID = iD;
		this.author = author;
		this.title = title;
	}
	
	public Book(Integer iD, String author, String title, Library library) {
		super();
		this.ID = iD;
		this.author = author;
		this.title = title;
		this.library = library;
	}
	
	public Book(Integer iD, String author, String title, Date issueDate, String reader) {
		super();
		this.ID = iD;
		this.author = author;
		this.title = title;
		this.issueDate = issueDate;
		this.reader = reader;
	}
	
	public Book(Integer iD, String author, String title, Date issueDate, String reader, Library library) {
		super();
		this.ID = iD;
		this.author = author;
		this.title = title;
		this.issueDate = issueDate;
		this.reader = reader;
		this.library = library;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
}
