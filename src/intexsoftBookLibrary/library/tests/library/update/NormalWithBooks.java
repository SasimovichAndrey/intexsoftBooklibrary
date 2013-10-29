package intexsoftBookLibrary.library.tests.library.update;

import static org.junit.Assert.*;

import java.sql.Timestamp;

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
		Library expected = new Library(null, "TestLibrary");
		Book book = new Book(null, "TestAuthor", "TestTitle");
		expected.addBook(book);
		
		saveLibrary(expected);
		
		expected.getBookList().get(0).setIssueDate(new Timestamp(System.currentTimeMillis()));
		expected.getBookList().get(0).setReader("TestReader");
		
		updateLibrary(expected);
		
		Library actual = getLibrary(expected.getId());
		
		assertEquals(expected, actual);
	}

}
