package tests.library.create;

import java.util.Date;

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

public class ErrorInvalidLibraryBooks extends CreateTest {

	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		super.Init();
		testLibrary = new Library(0, "With invalid books");
		testLibrary.addBook(new Book(null, "TestAuthor", null));
		testLibrary.addBook(new Book(null, "TestAuthor", "TestTitle", new Date(), null));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		thrown.expect(DAOException.class);
		libraryDAO.create(testLibrary);
	}

}
