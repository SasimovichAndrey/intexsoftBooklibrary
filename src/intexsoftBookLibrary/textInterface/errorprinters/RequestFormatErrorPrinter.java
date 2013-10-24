package intexsoftBookLibrary.textInterface.errorprinters;


public class RequestFormatErrorPrinter implements IErrorPrinter {

	@Override
	public void printError() {
		System.out.println("Invalid request format! ");
	}

}
