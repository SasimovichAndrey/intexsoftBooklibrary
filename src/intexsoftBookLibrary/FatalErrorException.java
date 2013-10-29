package intexsoftBookLibrary;

public class FatalErrorException extends Exception{
	public FatalErrorException(Throwable cause){
		super.initCause(cause);
	}
}
