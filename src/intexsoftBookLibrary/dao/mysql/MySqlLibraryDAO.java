package intexsoftBookLibrary.dao.mysql;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import intexsoftBookLibrary.dao.ILibraryDAO;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidLibraryFieldValueException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidNewLibraryBookException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

public class MySqlLibraryDAO implements ILibraryDAO{
	private Session session;

	@SuppressWarnings("unchecked")
	@Override
	public List<Library> getAllLibraries() throws DAOException {
		try{
 			this.session = HibernateConnector.getSession();
			
			Criteria crit = session.createCriteria(Library.class);
	
			return crit.list();
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception");
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new DAOException();
		}
	}

	@Override
	public Library getLibraryByBook(Book book) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer create(Library library) throws DAOException, InvalidLibraryFieldValueException, InvalidNewLibraryBookException {
		try{
 			this.session = HibernateConnector.getSession();
 			if(library.getName() == null)
 				throw new InvalidLibraryFieldValueException("Somelibrary fields are null");
 			if(library.getBooks() != null){
 				for(Book book : library.getBooks()){
 					if(book.getID() != null){
 						throw new InvalidNewLibraryBookException("All of book in new library must be with null-identifier");
 					}
 				}
 			}
 			session.beginTransaction();
 			Serializable ser = session.save(library);
 			session.getTransaction().commit();
 			return (Integer)ser;
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception");
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new DAOException();
		}
	}
	
	public Library getLibraryById(Integer id) throws DAOException{
		try{
 			this.session = HibernateConnector.getSession();
 			Library library = (Library)session.load(Library.class, id);
 			return library;
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception");
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new DAOException();
		}
	}
}
