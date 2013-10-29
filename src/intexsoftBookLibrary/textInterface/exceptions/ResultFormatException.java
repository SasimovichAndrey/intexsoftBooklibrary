package intexsoftBookLibrary.textInterface.exceptions;

public class ResultFormatException extends Exception {
	/*
	 * Отсутсвие необходимых для работы классов группы "printer" параметров в объекте RequestAnswer
	 */
	public ResultFormatException(){
		super();
	}
	
	public ResultFormatException(Throwable cause){
		super();
		super.initCause(cause);
	}
}
