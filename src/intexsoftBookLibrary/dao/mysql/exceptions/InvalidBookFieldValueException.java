package intexsoftBookLibrary.dao.mysql.exceptions;

public class InvalidBookFieldValueException extends Exception{
	/*
	 * Signals about book-field incorrect value (probably null-value)
	 */
	
	public InvalidBookFieldValueException(String message){
		super(message);
	}
	
	public InvalidBookFieldValueException(Throwable cause){
		super(cause);
	}
	
	public InvalidBookFieldValueException(String message, Throwable cause){
		super(message, cause);
	}
}
