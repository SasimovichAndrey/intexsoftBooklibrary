package intexsoftBookLibrary.library.exceptions;

public class IdentifierAlreadyExiststException extends Exception {
	/*
	 * Illegal attempt to add book with id, existed in library-books
	 */
	public IdentifierAlreadyExiststException(String message){
		super(message);
	}
	public IdentifierAlreadyExiststException(String message, Throwable cause){
		super(message, cause);
	}
	public IdentifierAlreadyExiststException(Throwable cause){
		super(cause);
	}
}
