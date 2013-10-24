package tests.library.create;

import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ErrorWithExistedBooks extends CreateTest {
	private Library existedLibrary;
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		super.Init();
		existedLibrary = new Library(null, "ExistedLibrary");
		Book bookInExLibrary = new Book(null, "ExistedBook", "TestTitle", existedLibrary);
		existedLibrary.addBook(bookInExLibrary);
		libraryDAO.create(existedLibrary);
		
		testLibrary = new Library(null, "TestLibrary");
		testLibrary.addBook(bookInExLibrary);
	}

	@After
	public void tearDown() throws Exception {
		libraryDAO.delete(existedLibrary);
	}

	@Test
	public void test() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		thrown.expect(InvalidNewLibraryBookException.class);
		libraryDAO.create(testLibrary);
	}

}
