package intexsoftBookLibrary.library.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	protected List<Library> testLibraries = new ArrayList<Library>();
	
	private void closeSession(){
		if(session != null && session.isOpen())
			session.close();
	}
	
	protected static void init(boolean schemaExport) throws HiberanteConfigException{
		String cfgFile = "test.xml";
		
		Properties cfgProp = new Properties();
		cfgProp.setProperty("hbm2ddl.auto", "create-drop");
		cfgProp.setProperty("hibernate.jdbc.batch_size", "50");
		
		HibernateConnector.configureHibernate(cfgFile, cfgProp, schemaExport);
		
		//HibernateConnector.configureHibernate();
	}

	protected Integer saveLibrary(Library library) throws HibernateException, HiberanteConfigException{
		if(session != null && session.isOpen()){
			session.close();
		}
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Integer id = (Integer)session.save(library);
			session.getTransaction().commit();
			
			testLibraries.add(library);
			
			return id;
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	
	protected Integer saveBook(Book book) throws HibernateException, HiberanteConfigException{
		closeSession();
		try{
			session = HibernateConnector.getSession();
			
			session.beginTransaction();
			Integer id =(Integer)session.save(book);
			session.getTransaction().commit();
			
			return id;
		}
		finally{
			closeSession();
		}
	}
	
	protected Book getBookById(Integer id) throws HibernateException, HiberanteConfigException{
		closeSession();
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Book book = (Book)session.get(Book.class, id);
			session.getTransaction().commit();
			
			return book;
		}
		finally{
			closeSession();
		}
	}

	protected void updateBook(Book book) throws HibernateException, HiberanteConfigException{
		closeSession();
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			session.update(book);
			session.getTransaction().commit();
		}
		finally{
			closeSession();
		}
	}
	
	protected void deleteLibrary(Library library) throws HibernateException, HiberanteConfigException{
		if(session != null && session.isOpen()){
			session.close();
		}
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			session.delete(library);
			session.getTransaction().commit();
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	
	protected Library getLibrary(Integer id) throws HibernateException, HiberanteConfigException{
		if(session != null && session.isOpen()){
			session.close();
		}
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Library library = (Library)session.get(Library.class, id);
			session.getTransaction().commit();
			return library;
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	
	protected void updateLibrary(Library library) throws HibernateException, HiberanteConfigException{
		if(session != null && session.isOpen()){
			session.close();
		}
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			session.update(library);
			session.getTransaction().commit();
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	
	protected Book getBook(Integer id) throws HibernateException, HiberanteConfigException{
		if(session != null && session.isOpen())
			session.close();
		try{
			session = HibernateConnector.getSession();
			session.beginTransaction();
			Book book = (Book)session.get(Book.class, id);
			session.getTransaction().commit();
			
			return book; 
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	
	protected void finish() throws HibernateException, HiberanteConfigException{
		closeSession();
		session = HibernateConnector.getSession();
		try{
			for(Library library : testLibraries){
				try{
					session.beginTransaction();
					session.delete(library);
					session.getTransaction().commit();
				}
				catch(HibernateException e){
					e.printStackTrace(System.out);
				}
			}
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
}
