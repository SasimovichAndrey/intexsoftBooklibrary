package intexsoftBookLibrary.library;

import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;

import java.util.ArrayList;
import java.util.List;


public class Library{
	private List<Book> books = new ArrayList<Book>();;
	protected String name;
	protected Integer id;
	
	public Library(){
	}
	
	public Library(Integer id, String name) {
		this.books = new ArrayList<Book>();
		this.name = name;
	}
	
	public Library(String name){
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
		
		if(!exists){
			book.setLibrary(this);
			books.add(book);
		}
		else{
			throw new IdentifierAlreadyExiststException("A book with the same id already existed in this library. ID=" + book.getID());
		}
	}
	
	public void removeBook(Book book){
		if(books.contains(book)){
			books.remove(book);
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

	public List<Book> getBooks(){
		return books;
	}
	
	public Book getBookByIndex(int bookIndex){
		return books.get(bookIndex);
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

	public boolean equals(Object object){
		if(!(object instanceof Library))
			return false;
		Library obj = (Library)object;
		// Проверка id
		if(id == null ^ obj.id == null){
				return false;
		}
		else if(!(id == null && obj.id == null || id.equals(obj.id))){
			return false;
		}
		
		//Проверка name
		if(name == null ^ obj.name == null){
			return false;
		}
		else if(!(name == null && obj.name == null || name.equals(obj.name))){
			return false;
		}
		
		// Проверка books
		if(books == null ^ obj.books == null){
			return false;
		}
		else if(books == null && obj.books == null){
			return true;
		}
		else if(books.size() == obj.books.size()){
			for(int i = 0; i < books.size(); i++){
				if(!(books.get(i).equals(obj.books.get(i))))
					return false;
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean equals2(Object object){
		if(object instanceof Library){
			Library testObject = (Library)object;
			if(name.equals(testObject.name) && (int)id == (int)testObject.id){
				if(books != null && testObject.books != null){
					if(books.size() == testObject.books.size()){
						for(int i = 0; i < books.size(); i++){
							if(!(books.get(i).equals(testObject.books.get(i)))){
								return false;
							}
						}
						return true;
					}
					else
						return false;
				}
				else{
					return true;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
}
