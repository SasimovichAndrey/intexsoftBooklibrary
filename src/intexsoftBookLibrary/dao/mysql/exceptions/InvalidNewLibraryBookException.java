package intexsoftBookLibrary.dao.mysql.exceptions;

public class InvalidNewLibraryBookException extends Exception {
	/*
	 * Illegal attempt to create library with existed books in database
	 */
	public InvalidNewLibraryBookException(String message){
		super(message);
	}
	public InvalidNewLibraryBookException(String message, Throwable cause){
		super(message, cause);
	}
	public InvalidNewLibraryBookException(Throwable cause){
		super(cause);
	}

}
