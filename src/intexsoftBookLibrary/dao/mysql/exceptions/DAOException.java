package intexsoftBookLibrary.dao.mysql.exceptions;

public class DAOException extends Exception {
	/*
	 * ������ DAO
	 */
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(){
		super();
	}
}
