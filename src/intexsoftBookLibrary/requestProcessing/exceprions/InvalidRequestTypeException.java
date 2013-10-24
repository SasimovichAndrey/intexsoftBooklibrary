package intexsoftBookLibrary.requestProcessing.exceprions;

public class InvalidRequestTypeException extends Exception{
	public InvalidRequestTypeException(String message) {
		super(message);
	}
	
	public InvalidRequestTypeException() {
	}
}
