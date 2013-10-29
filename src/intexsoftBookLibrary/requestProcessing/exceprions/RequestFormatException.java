package intexsoftBookLibrary.requestProcessing.exceprions;

public class RequestFormatException extends Exception{
	public RequestFormatException(String message, Throwable cause){
		super(message, cause);
	}
	
	public RequestFormatException(String message){
		super(message);
	}
}
