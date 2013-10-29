package intexsoftBookLibrary.library;

import java.sql.Timestamp;

public class Book {
	
	private Integer ID;
	private String author;
	private String title;
	private Timestamp issueDate;
	private String reader;
	
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
	
	public Book(Integer iD, String author, String title, Timestamp issueDate, String reader) {
		super();
		this.ID = iD;
		this.author = author;
		this.title = title;
		this.issueDate = issueDate;
		if(this.issueDate != null)
			this.issueDate.setNanos(0);
		this.reader = reader;
	}
	
	public Book(Integer iD, String author, String title, Timestamp issueDate, String reader, Library library) {
		super();
		this.ID = iD;
		this.author = author;
		this.title = title;
		this.issueDate = issueDate;
		if(this.issueDate != null)
			this.issueDate.setNanos(0);
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

	public Timestamp getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
		if(this.issueDate != null)
			this.issueDate.setNanos(0);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
