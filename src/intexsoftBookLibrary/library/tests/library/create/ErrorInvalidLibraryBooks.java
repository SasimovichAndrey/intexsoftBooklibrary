package intexsoftBookLibrary.library.tests.library.create;

import java.sql.Timestamp;
import java.util.Date;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.library.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ErrorInvalidLibraryBooks extends LibraryTest {

	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		super.init();
	}

	@After
	public void tearDown() throws Exception {
		finish();
	}

	private void saveWithBook(Book book) throws HibernateException, HiberanteConfigException, IdentifierAlreadyExiststException{
		Library library = new Library(null, "TestLibrary");
		library.addBook(book);
		
		thrown.expect(HibernateException.class);
		
		saveLibrary(library);
	}
	
	@Test
	public void test() throws IdentifierAlreadyExiststException, HibernateException, HiberanteConfigException {
		Book invalidBook = new Book(null, null, "TestTitle", new Timestamp(new Date().getTime()), "TestReader");
		
		thrown.expect(HibernateException.class);
		
		saveWithBook(invalidBook);
	}
	
	@Test
	public void test2() throws IdentifierAlreadyExiststException, HibernateException, HiberanteConfigException {
		Book invalidBook = new Book(null, "TestAuthor", null , new Timestamp(new Date().getTime()), "TestReader");
		
		thrown.expect(HibernateException.class);
		
		saveWithBook(invalidBook);
	}
}
