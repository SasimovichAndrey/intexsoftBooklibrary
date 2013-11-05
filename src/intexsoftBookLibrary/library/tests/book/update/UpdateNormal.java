package intexsoftBookLibrary.library.tests.book.update;

import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.LibraryTest;

public class UpdateNormal extends LibraryTest {

	@BeforeClass
	public static void setUp() throws Exception {
		init(true);
	}

	@After
	public void tearDown() throws Exception {
		finish();
	}

	@Test
	public void test() throws HibernateException, HiberanteConfigException, IdentifierAlreadyExiststException {
		Library testLibrary = new Library("TestLibrary");
		
		saveLibrary(testLibrary);
		
		Book expected = new Book("TestAuthor", "TestTitle");
		testLibrary.addBook(expected);
		
		saveBook(expected);
		
		expected.setAuthor("Changed Author");
		
		updateBook(expected);
		
		Book actual = getBookById(expected.getID());
		
		assertEquals(expected, actual);
	}

}
