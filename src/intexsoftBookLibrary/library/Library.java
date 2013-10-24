package intexsoftBookLibrary.library;

import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;

import java.util.ArrayList;
import java.util.List;


public class Library{
	private List<Book> books;
	protected String name;
	protected Integer id;
	
	public Library(){
		
	}
	
	public Library(Integer id, String name) {
		this.books = new ArrayList<Book>();
		this.name = name;
	}
	
	public void setBooks(List<Book> books){
		this.books = books;
	}
	
	public void addBook(Book book) throws IdentifierAlreadyExiststException{
		boolean exists = Boolean.FALSE;
		for(Book exBook : books){
			if(exBook.getID() != null)
				if(exBook.getID() == book.getID()){
					exists = Boolean.TRUE;
					break;
				}
		}
		
		if(!exists)
			books.add(book);
		else{
			throw new IdentifierAlreadyExiststException("A book with the same id already existed in this library");
		}
	}
	
	public Book getBook(int id){
		for(int i = 0; i < books.size(); i++){
			if(books.get(i).getID() == id){
				return books.get(i);
			}
		}
		return null;
	}
	
	public int getBookCount(){
		return books.size();
	}
	
	public List<Book> getBookList(){
		return books;
	}

	public Book getBookByIndex(int bookIndex){
		return books.get(bookIndex);
	}

	public List<Book> getBooks(){
		return books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
