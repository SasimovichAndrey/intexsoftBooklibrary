package intexsoftBookLibrary.library.tests.library.update;

import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.tests.LibraryTest;

public class NormalWithoutBooks extends LibraryTest {
	
	@Before
	public void setUp() throws Exception {
		super.init(false);
	}

	@After
	public void tearDown() throws Exception {
		super.finish();
	}

	@Test
	public void test() throws HibernateException, HiberanteConfigException {
		Library expected = new Library(null, "TestLibrary");
		
		saveLibrary(expected);
		
		expected.setName("ChangedLibrary");
		
		updateLibrary(expected);
		
		Library actual = getLibrary(expected.getId());
		
		assertEquals(expected, actual);
	}

}
