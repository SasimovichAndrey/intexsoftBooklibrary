package intexsoftBookLibrary.requestProcessing.exceprions;

public class DataBaseAccessException extends Exception {
	public DataBaseAccessException(){}
	
	public DataBaseAccessException(String message) {
		super(message);
	}
	/*
	 * Ошибка подключения к базе данных
	 */
}
