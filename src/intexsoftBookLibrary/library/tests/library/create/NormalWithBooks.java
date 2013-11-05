package intexsoftBookLibrary.library.tests.library.create;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormalWithBooks extends LibraryTest {

	@Before
	public void setUp() throws Exception {
		super.init(false);
	}

	@After
	public void tearDown() throws Exception {
		finish();
	}

	@Test
	public void test() throws InvalidLibraryFieldValueException, InvalidNewLibraryBookException, IdentifierAlreadyExiststException, HibernateException, HiberanteConfigException {
		// �������� �������� ����������
		Library expected = new Library(null, "WithBooks");

		//���������� ���� � �������� ����������
		expected.addBook(new Book(null, "TestAuthor", "TestTitle"));
		expected.addBook(new Book(null, "TestAuthor", "TestTitle", new Timestamp(new Date().getTime()), "TestReader"));
		
		//���������� ���������� � ��
		saveLibrary(expected);
		
		//������ ���������� ����������
		Library actual = getLibrary(expected.getId());

		expected.equals(actual);
		
		assertEquals(expected, actual);
	}
}
