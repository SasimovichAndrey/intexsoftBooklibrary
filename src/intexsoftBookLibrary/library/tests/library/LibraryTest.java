package intexsoftBookLibrary.library.tests.library;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import intexsoftBookLibrary.dao.mysql.HibernateConnector;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

public class LibraryTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	protected Session session;
	protected List<Library> testLibraries;
	
	protected void init() throws HiberanteConfigException{
		HibernateConnector.configureHibernate();
		testLibraries = new ArrayList<Library>();
	}

	protected Integer saveLibrary(Library library) throws HibernateException, HiberanteConfigException{
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Integer id = (Integer)session.save(library);
			session.getTransaction().commit();
			session.close();
			
			testLibraries.add(library);
			
			return id;
		}
		finally{
			if(session.isOpen())
				session.close();
		}
	}
	
	protected void deleteLibrary(Library library) throws HibernateException, HiberanteConfigException{
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			session.delete(library);
			session.getTransaction().commit();
		}
		finally{
			if(session.isOpen())
				session.close();
		}
	}
	
	protected Library getLibrary(Integer id) throws HibernateException, HiberanteConfigException{
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Library library = (Library)session.get(Library.class, id);
			session.getTransaction().commit();
			return library;
		}
		finally{
			if(session.isOpen())
				session.close();
		}
	}
	
	protected void updateLibrary(Library library) throws HibernateException, HiberanteConfigException{
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			session.update(library);
			session.getTransaction().commit();
		}
		finally{
			if(session.isOpen())
				session.close();
		}
	}
	
	protected Book getBook(Integer id) throws HibernateException, HiberanteConfigException{
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Book book = (Book)session.get(Book.class, id);
			session.getTransaction().commit();
			
			return book; 
		}
		finally{
			session.close();
		}
	}
	
	protected void finish() throws HibernateException, HiberanteConfigException{
		if(session.isOpen())
			session.close();
		session = HibernateConnector.getSession();
		for(Library library : testLibraries){
			try{
				session.beginTransaction();
				session.delete(library);
				session.getTransaction().commit();
			}
			catch(HibernateException e){
				e.printStackTrace(System.out);
				continue;
			}
		}
		session.close();
	}
}
