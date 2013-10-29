package intexsoftBookLibrary.requestProcessing.exceprions;

public class DataBaseAccessException extends Exception {
	public DataBaseAccessException(){}
	
	public DataBaseAccessException(String message) {
		super(message);
	}
	
	public DataBaseAccessException(Throwable cause){
		super();
		super.initCause(cause);
	}
	/*
	 * Ошибка подключения к базе данных
	 */
}
