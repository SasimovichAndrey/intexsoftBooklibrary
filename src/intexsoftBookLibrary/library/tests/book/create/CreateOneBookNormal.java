package intexsoftBookLibrary.library.tests.book.create;

import static org.junit.Assert.*;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateOneBookNormal extends LibraryTest{

	@BeforeClass
	public static void setUp() throws Exception {
		init(true);
	}
	
	@After
	public void tearDown(){
		
	}

	@Test
	public void test() throws HibernateException, HiberanteConfigException, IdentifierAlreadyExiststException {
		Library bookLib = new Library(null, "TestLibrary");
		Book expected = new Book(null, "TestAuthor", "TestTitle");
		
		saveLibrary(bookLib);
		
		bookLib.addBook(expected);
		
		saveBook(expected);
		
		Book actual = getBookById(expected.getID());
		
		assertEquals(expected, actual);
	}

}
