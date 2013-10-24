package tests.library.create;

import static org.junit.Assert.*;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Library;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormaWithoutBooks extends CreateTest {	
	@Before
	public void setUp() throws Exception {
		super.Init();
		testLibrary = new Library(0, "TestLibrary");
	}

	@After
	public void tearDown() throws Exception {
		libraryDAO.delete(testLibrary);
	}

	@Test
	public void test() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		Integer id = libraryDAO.create(testLibrary);
		Matcher<Integer> zeroOrGreater = getMatcher();
		assertThat(id, zeroOrGreater);
		testLibrary.setId(id);
	}
}
