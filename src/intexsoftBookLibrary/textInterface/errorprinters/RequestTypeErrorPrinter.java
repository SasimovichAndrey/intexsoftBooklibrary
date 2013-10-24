package intexsoftBookLibrary.textInterface.errorprinters;

public class RequestTypeErrorPrinter implements IErrorPrinter {

	@Override
	public void printError() {
		System.out.println("Invalid request type. Try again please :) ");
	}

}
