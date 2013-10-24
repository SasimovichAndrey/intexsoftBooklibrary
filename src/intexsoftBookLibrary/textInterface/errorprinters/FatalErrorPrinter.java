package intexsoftBookLibrary.textInterface.errorprinters;

public class FatalErrorPrinter implements IErrorPrinter {

	@Override
	public void printError() {
		System.out.println("Fatal error. Something was going really wrong. Can't work anymore. ");
	}

}
