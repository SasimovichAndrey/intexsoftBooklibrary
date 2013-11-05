package intexsoftBookLibrary.library.tests.library.create;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.tests.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ErrorInvalidLibraryFields extends LibraryTest{
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
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
		Library testLibrary = new Library(null, null);
		
		thrown.expect(HibernateException.class);
		
		saveLibrary(testLibrary);
		
		testLibraries.add(testLibrary);
	}
}
