package intexsoftBookLibrary.textInterface.exceptions;

public class ResultFormatException extends Exception {
	/*
	 * ��������� ����������� ��� ������ ������� ������ "printer" ���������� � ������� RequestAnswer
	 */
	public ResultFormatException(){
		super();
	}
	
	public ResultFormatException(Throwable cause){
		super();
		super.initCause(cause);
	}
}
