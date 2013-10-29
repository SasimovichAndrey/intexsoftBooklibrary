package intexsoftBookLibrary.dao.mysql.exceptions;

public class DAOException extends Exception {
	/*
	 * Ошибка DAO
	 */
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(){
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
