package intexsoftBookLibrary.library.tests.library.delete;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.library.LibraryTest;

public class ErrorRemovedBookFromList extends LibraryTest {
	private Integer deletedBookID;

	@Before
	public void setUp() throws Exception {
		super.init();
	}

	@After
	public void tearDown() throws Exception {
		testLibraries.get(0).addBook(getBook(deletedBookID));
		super.finish();
	}

	@Test
	public void test() throws IdentifierAlreadyExiststException, HibernateException, HiberanteConfigException {
		Library testLibrary = new Library(null, "TestLibrary");
		Book book = new Book(null, "TestAuthor", "TestTitle");
		testLibrary.addBook(book);
		
		saveLibrary(testLibrary);
		
		deletedBookID = book.getID();
		
		testLibrary.removeBook(book);
		
		thrown.expect(HibernateException.class);
		
		deleteLibrary(testLibrary);
	}

}
