package intexsoftBookLibrary.library.tests.library.update;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorInvalidBookField extends LibraryTest{
	
	@Before
	public void setUp() throws Exception {
		super.init(false);
	}

	@After
	public void tearDown() throws Exception {
		super.finish();
	}

	@Test
	public void test() throws IdentifierAlreadyExiststException, HibernateException, HiberanteConfigException {
		Library expected = new Library(null, "TestLibrary");
		Book book = new Book(null, "TestAuthor", "TestTitle");
		expected.addBook(book);
		
		saveLibrary(expected);
		
		expected.getBookList().get(0).setAuthor(null);
		
		thrown.expect(HibernateException.class);
		
		updateLibrary(expected);
	}
}
