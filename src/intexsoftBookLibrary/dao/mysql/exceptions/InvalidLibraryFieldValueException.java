package intexsoftBookLibrary.dao.mysql.exceptions;

public class InvalidLibraryFieldValueException extends Exception {
	/*
	 * Signals about library-field incorrect value (probably null-value)
	 */
	
	public InvalidLibraryFieldValueException(String message){
		super(message);
	}
	
	public InvalidLibraryFieldValueException(Throwable cause){
		super(cause);
	}
	
	public InvalidLibraryFieldValueException(String message, Throwable cause){
		super(message, cause);
	}
}
