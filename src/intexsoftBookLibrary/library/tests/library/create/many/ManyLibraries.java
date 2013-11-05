package intexsoftBookLibrary.library.tests.library.create.many;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import intexsoftBookLibrary.dao.mysql.HibernateConnector;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.exceptions.IdentifierAlreadyExiststException;
import intexsoftBookLibrary.library.tests.LibraryTest;

public class ManyLibraries extends LibraryTest {

	private static final int libCount = 100;
	private static final int bookCount = 1000;
	
	private static final String emptyStr = "";
	
	private static List<Library> libraryList = new ArrayList<Library>();
	
	@BeforeClass
	public static void beforeClass() throws HiberanteConfigException{
		init(false);
		for(int lib = 0; lib < libCount; lib++){
			Library library = new Library(null, emptyStr);
			Book book = null;
			ArrayList<Book> bookList = new ArrayList<Book>();
			for(int bk = 0; bk < bookCount; bk++){
				book = new Book(null, emptyStr, emptyStr);
				book.setLibrary(library);
				bookList.add(book);
			}
			library.setBooks(bookList);
			libraryList.add(library);
		}
	}
	
	public static void tearDown() throws HibernateException, HiberanteConfigException{
	}
	
	//@Ignore
	@Test
	public void testSql() throws HibernateException, HiberanteConfigException{ // 13,5 sec
		StatelessSession session = HibernateConnector.getStatelessSession();
		
		session.beginTransaction();
		for(Library library : libraryList){
			session.insert(library);
			testLibraries.add(library);
		}
		session.getTransaction().commit();
		
		StringBuilder query = new StringBuilder("insert into books(author, title, lib_Id) values");
		
		Iterator<Library> it = libraryList.iterator();
		while(it.hasNext()){
			Library lib = it.next();
			List<Book> books = lib.getBooks();
			Iterator<Book> bookIt = books.iterator();
			Book book = null;
			while(bookIt.hasNext()){
				book = bookIt.next();
				query.append("(\'");
				query.append(book.getAuthor());
				query.append("\','");
				query.append(book.getTitle());
				// !!
				query.append("\',");
				query.append(lib.getId());
				query.append(')');
				if(bookIt.hasNext())
					query.append(',');
			}
			if(it.hasNext())
				query.append(',');
		}
		query.append(';');
		
		session.beginTransaction();
		session.createSQLQuery(query.toString()).executeUpdate();
		session.getTransaction().commit();
		
		session.close();
	}
	
	@Ignore
	@Test
	public void testSave() throws HibernateException, HiberanteConfigException, IdentifierAlreadyExiststException{ // 25 sec (100 lib, 1000 books)
	
		session = HibernateConnector.getSession();
		
		session.beginTransaction();
		
		Iterator<Library> it = libraryList.iterator();
		session.setCacheMode(CacheMode.IGNORE);
		session.setFlushMode(FlushMode.MANUAL);
		
		while(it.hasNext()){
			Library next = it.next();
			session.save(next);
			session.flush();
			session.clear();
			testLibraries.add(next);
			
		}
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	@Ignore
	@Test
	public void testStateless() throws HibernateException, HiberanteConfigException, IdentifierAlreadyExiststException { //27 sec
		
		StatelessSession stateLesssSession = HibernateConnector.getStatelessSession();

		stateLesssSession.beginTransaction();
		
		Iterator<Library> it = libraryList.iterator();

		while(it.hasNext()){
			Library lib = it.next();
			stateLesssSession.insert(lib);
			List<Book> books = lib.getBooks();
			Iterator<Book> bookIt = books.iterator();
			Book book = null;
			while(bookIt.hasNext()){
				book = bookIt.next();
				stateLesssSession.insert(book);
			}
			testLibraries.add(lib);
		}
		
		stateLesssSession.getTransaction().commit();
		
		stateLesssSession.close();
	}
}
