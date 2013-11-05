package intexsoftBookLibrary.dao.mysql;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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
			throw new HiberanteConfigException(e);
		}
	}

	// Конфигурация для тестов. Auto create/drop tables in 'TH' database
	@SuppressWarnings("deprecation") 
	public static void configureHibernate(String cfgFile, Properties properties, boolean exportSchema) throws HiberanteConfigException{
		try{
			if(isConfigured == false){
				Configuration cfg = new Configuration();
				
				cfg = cfg.configure(cfgFile);

				Set<String> propSet = properties.stringPropertyNames();
				Iterator<String> it = propSet.iterator();
				
				while(it.hasNext()){
					String key = it.next();
					cfg.setProperty(key, properties.getProperty(key));
				}
				
				if(exportSchema){
					SchemaExport schemaExport = new SchemaExport(cfg);
					schemaExport.create(false, true);
				}
				factory = cfg.buildSessionFactory();
				isConfigured = true;
			}
		}
		catch(HibernateException e){
			e.printStackTrace(System.out);
			throw new HiberanteConfigException(e);
		}
	}
	
	public static Session getSession() throws HiberanteConfigException, HibernateException{
		if(isConfigured == false){
			configureHibernate();
		}
		Session session = factory.openSession();
		
		return session;
	}
	public static StatelessSession getStatelessSession() throws HiberanteConfigException{
		if(isConfigured == false){
			configureHibernate();
		}
		
		return factory.openStatelessSession();
	}
}
