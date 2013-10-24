package tests.library.create;

import static org.junit.Assert.*;

import java.util.Date;

import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormalWithBooks extends CreateTest {

	@Before
	public void setUp() throws Exception {
		super.Init();
		this.testLibrary = new Library(null, "WithBooks");
		testLibrary.addBook(new Book(null, "TestAuthor", "TestTitle", testLibrary));
		testLibrary.addBook(new Book(null, "TestAuthor", "TestTitle", new Date(), "TestReader", testLibrary));
	}

	@After
	public void tearDown() throws Exception {
		libraryDAO.delete(testLibrary);
	}

	@Test
	public void test() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		Integer id = libraryDAO.create(testLibrary);

		Matcher<Integer> zeroOrGreater = getMatcher();
		assertThat(id, zeroOrGreater);
		testLibrary.setId(id);
	}

}
