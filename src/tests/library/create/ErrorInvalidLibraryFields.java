package tests.library.create;

import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Library;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ErrorInvalidLibraryFields extends CreateTest{
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		super.Init();
		testLibrary =  new Library();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		thrown.expect(InvalidLibraryFieldValueException.class);
		libraryDAO.create(testLibrary);
	}

}
