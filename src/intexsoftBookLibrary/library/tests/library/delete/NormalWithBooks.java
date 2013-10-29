package intexsoftBookLibrary.library.tests.library.delete;

import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.library.LibraryTest;

public class NormalWithBooks extends LibraryTest {

	@Before
	public void setUp() throws Exception {
		super.init();
	}

	@After
	public void tearDown() throws Exception {
		super.finish();
	}

	@Test
	public void test() throws HibernateException, HiberanteConfigException, IdentifierAlreadyExiststException {
		Library testLibrary = new Library(null, "TestLibrary");
		Book book = new Book(null, "TestAuthor", "TestTitle");
		testLibrary.addBook(book);
		
		saveLibrary(testLibrary);
		
		Integer index = testLibrary.getId();
		Integer bookIndex = book.getID();
		
		deleteLibrary(testLibrary);
		
		Library actual = getLibrary(index);
		Book actualBook = getBook(bookIndex);
		
		assertArrayEquals(new Object[]{actual, actualBook}, new Object[]{null, null});
	}

}
