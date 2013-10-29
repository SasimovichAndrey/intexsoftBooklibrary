package intexsoftBookLibrary;

import intexsoftBookLibrary.dao.mysql.HibernateConnector;
import intexsoftBookLibrary.dao.mysql.MySqlConstants;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.requestProcessing.MenuRequest;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.RequestController;
import intexsoftBookLibrary.requestProcessing.RequestParser;
import intexsoftBookLibrary.requestProcessing.RequestType;
import intexsoftBookLibrary.requestProcessing.exceprions.DataBaseAccessException;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;
import intexsoftBookLibrary.requestProcessing.exceprions.UnknownRequestTypeException;
import intexsoftBookLibrary.textInterface.ErrorType;
import intexsoftBookLibrary.textInterface.TextInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;

import org.hibernate.HibernateException;


public class ConsoleBookLibrary {
	private static TextInterface txtInterface;
	private static RequestController reqProcessor;
	
	public static void initialize() throws FileNotFoundException, ClassNotFoundException, SQLException, HiberanteConfigException, DataBaseAccessException{
		HashMap<String, String> propertyMap = new IniParser().parse(new File("properties.ini"));
		MySqlConstants.Initialize(propertyMap);
		try {
			HibernateConnector.configureHibernate();
		} catch(HibernateException e){
			throw new DataBaseAccessException(e);
		}
		reqProcessor = new RequestController();
		txtInterface = new TextInterface();
	}
	
	public static void finish(){
		txtInterface.Finish();
	}
	
	public static void programLoop(){
		String request;
		while(true){
			request = txtInterface.ReadRequest();
			//request = "return id=54";
			try{
				MenuRequest menuRequest = RequestParser.parse(request);
				RequestAnswer answer = reqProcessor.processRequest(menuRequest);
				
				if(answer.getReqType() == RequestType.EXIT){
					finish();
					break;
				}
				txtInterface.printReport(answer);
			}
			catch(UnknownRequestTypeException e){
				txtInterface.printError(intexsoftBookLibrary.textInterface.ErrorType.INVALID_REQUEST_TYPE);
			}
			catch(RequestFormatException e){
				txtInterface.printError(intexsoftBookLibrary.textInterface.ErrorType.INVALID_REQUEST_FORMAT);
			} catch (DataBaseAccessException e) {
				txtInterface.printError(ErrorType.DATABASE_ACCES_ERROR);
			}
		}
	}
	
	public static void main(String args[]){
		try{
			initialize();
			programLoop();
		}
		catch(FileNotFoundException e){
			System.out.println("Fatal error. Ini-file is missed.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} catch (HiberanteConfigException e) {
			System.out.println("Fatal error. Hibernate configuration is not correct.");
			e.printStackTrace();
		} catch (DataBaseAccessException e) {
			System.out.println("Fatak error. Cant connect to database.");
			e.printStackTrace();
		}
	}
}
