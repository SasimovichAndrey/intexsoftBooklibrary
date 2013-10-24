package tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import intexsoftBookLibrary.dao.DAOFactory;
import intexsoftBookLibrary.dao.DbType;
import intexsoftBookLibrary.dao.ILibraryDAO;
import intexsoftBookLibrary.dao.mysql.HibernateConnector;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.*;
import org.junit.rules.*;

import static org.junit.Assert.*;

public class LibraryCreateTest {
	public static Library[] libraryData;
	public static ILibraryDAO libraryDAO;
	public static List<Book> normBooks = new ArrayList<Book>();
	public static List<Book> invalidBooks = new ArrayList<Book>();
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		HibernateConnector.configureHibernate();
		
		libraryData = new Library[5];
		libraryData[0] = new Library(0, "TestLibrary"); // Normal without books
		libraryData[1] = new Library(); // Empty library
		libraryData[2] = new Library(0, "WithBooks"); // Library with valid books
		libraryData[2].addBook(new Book(null, "TestAuthor", "TestTitle", libraryData[2]));
		libraryData[2].addBook(new Book(null, "TestAuthor", "TestTitle", new Date(), "TestReader", libraryData[2]));
		libraryData[3] = new Library(0, "With invalid books"); // Library with invalid books
		libraryData[3].addBook(new Book(null, "TestAuthor", null));
		libraryData[3].addBook(new Book(null, "TestAuthor", "TestTitle", new Date(), null));
		libraryData[4] = new Library(0, "With existed books"); // Library with books, existed on a database
		libraryData[4].addBook(new Book(null, "TestAuthor", "Existed Book"));
		libraryData[4].addBook(new Book(10, "TestAuthor", "Existed", new Date(), "testReader"));
		
		
		DAOFactory factory = DAOFactory.getFactory(DbType.MYSQL);
		libraryDAO = factory.getLibraryDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	private Matcher<Integer> getMatcher(){
		Matcher<Integer> zeroOrGreater = new CustomMatcher<Integer>("zero or less") {
			   public boolean matches(Object object) {
			     return ((object instanceof Integer) && ((Integer)object >= 0));
			   }
			 };
		return zeroOrGreater;
	}
	
	@Test
	public void testNormLibraryWithoutBooksCreate() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		Integer id = libraryDAO.create(libraryData[0]);
		Matcher<Integer> zeroOrGreater = getMatcher();
		assertThat(id, zeroOrGreater);
	}
	
	@Test
	public void testCreateInvalidLibrary() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException{
		thrown.expect(InvalidLibraryFieldValueException.class);
		libraryDAO.create(libraryData[1]);
	}
	
	@Test
	public void testCreateLibraryWithBooks() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException{
		Integer id2 = libraryDAO.create(libraryData[2]);
		Matcher<Integer> zeroOrGreater = getMatcher();
		assertThat(id2, zeroOrGreater);
	}
	
	@Test
	public void testCreateLibraryWithExistedBooks() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException{
		thrown.expect(InvalidNewLibraryBookException.class);
		libraryDAO.create(libraryData[4]);
	} 
	
	@Test
	public void testCreateLibraryWithInvalidBooks() throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException{
		thrown.expect(DAOException.class);
		libraryDAO.create(libraryData[3]);
	}
}

