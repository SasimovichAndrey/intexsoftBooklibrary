package intexsoftBookLibrary.library.tests.library.create;

import static org.junit.Assert.*;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.library.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormalWithExistedBooks extends LibraryTest {
	
	@Before
	public void setUp() throws Exception {
		super.init();
	}

	@After
	public void tearDown() throws Exception {
		finish();
	}
	
	@Test
	public void test() throws IdentifierAlreadyExiststException, HibernateException, HiberanteConfigException {
		// Создание первой тестовой библиотеки с книгами
		Library existedLibrary = new Library(null, "TestLibrary");
		Book existedBook = new Book(null, "TestAuthor", "TestTitle");
		existedLibrary.addBook(existedBook);
		
		// Сохранение в БД
		saveLibrary(existedLibrary);

		//Удаление книги из первой библиотеки
		existedLibrary.removeBook(existedBook);
		
		// Создание второй(основной) тестовой библиотеки
		Library expected = new Library(null, "TestLibrary2");
		expected.addBook(existedBook);
		
		//Сохранение в БД
		saveLibrary(expected);
		
		Library actual = getLibrary(expected.getId());
		
		assertEquals(expected, actual);
	}
}
