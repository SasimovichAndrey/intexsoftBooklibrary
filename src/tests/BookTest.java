package tests;

import static org.junit.Assert.*;

import java.util.Date;

import intexsoftBookLibrary.dao.DAOFactory;
import intexsoftBookLibrary.dao.DbType;
import intexsoftBookLibrary.dao.IBookDAO;
import intexsoftBookLibrary.dao.mysql.HibernateConnector;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidBookFieldValueException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookTest {
	public static Book [] bookData;
	public static IBookDAO bookDAO;
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		HibernateConnector.configureHibernate();
		
		
		
		bookData = new Book[5];
		bookData[0] = new Book(0, "Kerouac", "On the road");
		bookData[1] = new Book(0, null, "Vojna i mir");
		bookData[2] = new Book(0, "Pushkin", "BookName", new Date(), null);
		bookData[2] = new Book(0, "Pushkin", "BookName", null, "Boris");
		
		DAOFactory factory = DAOFactory.getFactory(DbType.MYSQL);
		bookDAO = factory.getBookDAO();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testCreate() {
		Book newBook = bookData[0];
		try {
			
			bookDAO.create(newBook);
			
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidBookFieldValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
