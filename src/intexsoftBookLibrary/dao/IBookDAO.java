package intexsoftBookLibrary.dao;

import intexsoftBookLibrary.dao.mysql.exceptions.BookNotFoundException;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidBookFieldValueException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import java.util.List;

public interface IBookDAO {
	
	public List<Book> getBooksByLibrary(Library library)  throws DAOException;
	
	public List<Book> read(Book book) throws DAOException;
	
	public Book getBookById(int id) throws DAOException;
	
	public void update(Book book) throws DAOException, BookNotFoundException;
	
	public void create(Book book) throws DAOException, InvalidBookFieldValueException;
}
