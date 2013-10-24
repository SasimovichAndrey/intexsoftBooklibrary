package tests.library.create;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;

import intexsoftBookLibrary.dao.DAOFactory;
import intexsoftBookLibrary.dao.DbType;
import intexsoftBookLibrary.dao.ILibraryDAO;
import intexsoftBookLibrary.dao.mysql.HibernateConnector;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Library;

public class CreateTest {
	protected Library testLibrary;
	protected ILibraryDAO libraryDAO;
	
	protected void Init() throws HiberanteConfigException{
		HibernateConnector.configureHibernate();
		
		DAOFactory factory = DAOFactory.getFactory(DbType.MYSQL);
		libraryDAO = factory.getLibraryDAO();
	}
	
	protected Matcher<Integer> getMatcher(){
		Matcher<Integer> zeroOrGreater = new CustomMatcher<Integer>("zero or less") {
			   public boolean matches(Object object) {
			     return ((object instanceof Integer) && ((Integer)object >= 0));
			   }
			 };
		return zeroOrGreater;
	}
}
