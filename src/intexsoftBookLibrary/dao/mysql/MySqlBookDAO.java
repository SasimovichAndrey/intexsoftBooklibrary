package intexsoftBookLibrary.dao.mysql;

import java.util.List;

import intexsoftBookLibrary.dao.IBookDAO;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.dao.mysql.exceptions.InvalidBookFieldValueException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.library.Library;

import java.text.SimpleDateFormat;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class MySqlBookDAO implements IBookDAO {
	private Session session;
	
	@Override
	public List<Book> read(Book book) throws DAOException {
		try{
 			this.session = HibernateConnector.getSession();
			
			Criteria crit = session.createCriteria(Book.class);
			
			//Формирование условий запроса
			if(book.getID() != null){
				crit.add(Restrictions.eq("id", book.getID()));
			}
			if(book.getAuthor() != null){
				crit.add(Restrictions.like("author", book.getAuthor() + "%"));
			}
			if(book.getTitle() != null){
				crit.add(Restrictions.like("title", book.getTitle() + "%"));
			}
			if(book.getIssueDate() != null){
				SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				String formattedDate = df.format(book.getIssueDate());
				crit.add(Restrictions.eq("issueDate", formattedDate));
			}
			if(book.getReader() != null){
				crit.add(Restrictions.like("reader", book.getReader() + "%"));
			}
			
			@SuppressWarnings("unchecked")
			List<Book> books = crit.list();
			
			return books;
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception", e);
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new DAOException(e);
		}
		finally{
			session.close();
		}
	}

	public void update(Book book) throws DAOException{ // Обработать ситуацию с несуществующей книгой
		try{
			this.session = HibernateConnector.getSession();
 			
 			session.beginTransaction();
 			session.update(book);
 			session.getTransaction().commit();
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception", e);
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new DAOException(e);
		}
		finally{
			session.close();
		}
	}
	
	@Override
	public List<Book> getBooksByLibrary(Library library) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookById(int id) throws DAOException {
		try{
			session = HibernateConnector.getSession();
			Book book = (Book)session.get(Book.class, (Integer)id);
			
			return book;
		}
		catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception", e);
		}
		finally{
			session.close();
		}
	}
	
	public void create(Book book) throws DAOException, InvalidBookFieldValueException{
		try{
			if(book.getAuthor() == null || book.getTitle() == null || book.getLibrary() == null || (book.getIssueDate() == null ^ book.getReader() == null))
				throw new InvalidBookFieldValueException("Any of Book fields are null");
			session = HibernateConnector.getSession();
			session.save(book);
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		catch (HiberanteConfigException e) {
			e.printStackTrace();
			throw new DAOException("Hibernate config exception", e);
		}
		finally{
			session.close();
		}
	}
}
