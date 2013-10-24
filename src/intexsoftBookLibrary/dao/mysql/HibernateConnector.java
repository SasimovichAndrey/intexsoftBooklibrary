package intexsoftBookLibrary.dao.mysql;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector {
	private static SessionFactory factory = null;
	private static boolean isConfigured = false;
	
	@SuppressWarnings("deprecation")
	public static void configureHibernate() throws HiberanteConfigException{
		try{
			if(isConfigured == false){
				factory = new Configuration().configure().buildSessionFactory();
			isConfigured = true;
			}
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new HiberanteConfigException();
		}
	}

	public static Session getSession() throws HiberanteConfigException, HibernateException{
		if(isConfigured == false){
			configureHibernate();
		}
		Session session = factory.openSession();
		
		return session;
	}
}
