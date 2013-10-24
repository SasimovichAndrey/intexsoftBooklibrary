package intexsoftBookLibrary.dao;

import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import java.util.List;

public interface ILibraryDAO {
	
	public List<Library> getAllLibraries() throws DAOException;
	
	public Library getLibraryByBook(Book book) throws DAOException;
	
	public Integer create(Library library) throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException;

	public Library getLibraryById(Integer id) throws DAOException;
}
